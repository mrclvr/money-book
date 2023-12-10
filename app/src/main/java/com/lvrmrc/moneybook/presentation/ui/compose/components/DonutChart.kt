package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.pow
import kotlin.math.sqrt

private const val TOTAL_ANGLE = 360.0f
private val STROKE_SIZE_UNSELECTED = 40.dp
private val STROKE_SIZE_SELECTED = 60.dp


private class ArcState(
    val selected: State = State.UNSELECTED
) {
    val stroke: Dp
        get() = when (selected) {
            State.SELECTED -> STROKE_SIZE_SELECTED
            State.UNSELECTED -> STROKE_SIZE_UNSELECTED
        }

    enum class State {
        SELECTED, UNSELECTED
    }
}

private class DrawingAngles(private val start: Float, private val end: Float) {
    fun isInsideAngle(angle: Float) = angle > this.start && angle < this.start + this.end
}

/**
 * Find the distance based on two points in a graph. Calculated using the pythagorean theorem.
 */
private fun findTouchDistanceFromCenter(center: Offset, touch: Offset) = sqrt((touch.x - center.x).pow(2) + (touch.y - center.y).pow(2))

/**
 * The touch point start from Canvas top left which ranges from (0,0) -> (canvas.width, canvas.height).
 * We need to normalize this point so that it's based on the canvas center instead.
 */
private fun Offset.findNormalizedPointFromTouch(canvasCenter: Offset) = Offset(this.x, canvasCenter.y + (canvasCenter.y - this.y))

/**
 * Calculate the touch angle based on the canvas center. Then adjust the angle so that
 * drawing starts from the 4th quadrant instead of the first.
 */
private fun calculateTouchAngleAccordingToCanvas(canvasCenter: Offset, normalizedPoint: Offset): Float {
    val angle = calculateTouchAngleInDegrees(canvasCenter, normalizedPoint)
    return adjustAngleToCanvas(angle).toFloat()
}

/**
 * Calculate touch angle in radian using atan2(). Afterwards, convert the radian to degrees to be
 * compared to other data points.
 */
private fun calculateTouchAngleInDegrees(canvasCenter: Offset, normalizedPoint: Offset): Double {
    val touchInRadian = kotlin.math.atan2(
        normalizedPoint.y - canvasCenter.y, normalizedPoint.x - canvasCenter.x
    )
    return touchInRadian * -180 / Math.PI // Convert radians to angle in degrees
}

/**
 * Start from 4th quadrant going to 1st quadrant, degrees ranging from 0 to 360
 */
private fun adjustAngleToCanvas(angle: Double) = (angle + TOTAL_ANGLE) % TOTAL_ANGLE

private fun handleCanvasTap(
    center: Offset,
    tapOffset: Offset,
    anglesList: List<DrawingAngles>,
    currentSelectedIndex: Int,
    currentStrokeValues: List<Float>,
    onItemSelected: (Int) -> Unit = {},
    onItemDeselected: (Int) -> Unit = {},
    onNoItemSelected: () -> Unit = {},
) {
    val normalized = tapOffset.findNormalizedPointFromTouch(center)
    val touchAngle = calculateTouchAngleAccordingToCanvas(center, normalized)
    val distance = findTouchDistanceFromCenter(center, normalized)

    var selectedIndex = -1
    var newDataTapped = false

    anglesList.forEachIndexed { idx, angle ->
        val stroke = currentStrokeValues[idx]
        if (angle.isInsideAngle(touchAngle)) {
            if (distance > (center.x - stroke) && distance < (center.x)) { // since it's a square center.x or center.y will be the same
                selectedIndex = idx
                newDataTapped = true
            }
        }
    }

    if (selectedIndex >= 0 && newDataTapped) {
        onItemSelected(selectedIndex)
    }
    if (currentSelectedIndex >= 0) {
        onItemDeselected(currentSelectedIndex)
        if (currentSelectedIndex == selectedIndex || !newDataTapped) {
            onNoItemSelected()
        }
    }
}

@Composable
fun DonutChart(
    chartSize: Dp = 100.dp,
    data: DonutChartDataList,
    gapPercentage: Float = 0.04f,
    animDuration: Int = 1000,
    animLaunched: Boolean = false,
    selectionView: @Composable (DonutChartData?) -> Unit = {},
    onAnimLaunched: () -> Unit = {}
) {
    var selectedIndex by remember { mutableIntStateOf(-1) }
    val animationTargetState = (0..data.items.size).map {
        remember { mutableStateOf(ArcState()) }
    }
    val anglesList: MutableList<DrawingAngles> = remember { mutableListOf() }
    val gapAngle = data.calculateGapAngle(gapPercentage)
    var center = Offset(0f, 0f)

    val animValues = (0..data.items.size).map {
        animateDpAsState(
            targetValue = animationTargetState[it].value.stroke, animationSpec = TweenSpec(400), label = "Animate arcs"
        )
    }

    // it is the diameter value of the Pie
    val animateSize by animateFloatAsState(
        targetValue = if (animLaunched) chartSize.value * 2f else 0f, animationSpec = tween(
            durationMillis = animDuration, delayMillis = 0, easing = LinearOutSlowInEasing
        ), label = "Animate size"
    )

    // if you want to stabilize the Pie Chart you can use value -90f
    // 90f is used to complete 1/4 of the rotation
    val animateRotation by animateFloatAsState(
        targetValue = if (animLaunched) 90f * 11f else 0f, animationSpec = tween(
            durationMillis = animDuration, delayMillis = 0, easing = LinearOutSlowInEasing
        ), label = "Animate rotation"
    )

    // to play the animation only once when the function is Created or Recomposed
    LaunchedEffect(key1 = true) {
        onAnimLaunched()
    }

    Box(
//        modifier = Modifier.size(animateSize.dp),
//            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier
            .size(chartSize * 2f)
            .rotate(animateRotation)
            .pointerInput(Unit) {
                detectTapGestures(onTap = { tapOffset ->
                    handleCanvasTap(center = center,
                        tapOffset = tapOffset,
                        anglesList = anglesList,
                        currentSelectedIndex = selectedIndex,
                        currentStrokeValues = animationTargetState.map { it.value.stroke.toPx() },
                        onItemSelected = { index ->
                            selectedIndex = index
                            animationTargetState[index].value = ArcState(
                                ArcState.State.SELECTED
                            )
                        },
                        onItemDeselected = { index ->
                            animationTargetState[index].value = ArcState(
                                ArcState.State.UNSELECTED
                            )
                        },
                        onNoItemSelected = {
                            selectedIndex = -1
                        })
                })
            }, onDraw = {
            val defaultStrokeWidth = STROKE_SIZE_UNSELECTED.toPx()
            center = this.center
            anglesList.clear()
            var lastAngle = 0f
            data.items.forEachIndexed { idx, item ->

                val sweepAngle = data.findSweepAngle(idx, gapPercentage)
                anglesList.add(DrawingAngles(lastAngle, sweepAngle))
                val strokeWidth = animValues[idx].value.toPx()

                drawArc(
                    color = item.color,
                    startAngle = lastAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    topLeft = Offset(defaultStrokeWidth / 2, defaultStrokeWidth / 2),
                    style = Stroke(strokeWidth, cap = StrokeCap.Butt),
                    size = Size(
                        size.width - defaultStrokeWidth, size.height - defaultStrokeWidth
                    )
                )
                lastAngle += sweepAngle + gapAngle
            }
        })
        selectionView(if (selectedIndex >= 0) data.items[selectedIndex] else null)
    }
}


val OxfordBlue = Color(0xFF01184a)
val MetallicYellow = Color(0xFFFFCE08)
val VividOrange = Color(0xFFFD5F00)
val Sapphire = Color(0xFF1259b8)
val RobingEggBlue = Color(0xFF0fd4C4)

val viewData = DonutChartDataList(
    listOf(
        DonutChartData(1200.0, Sapphire, label = "Food & Groceries"),
//        DonutChartData(1500.0, RobingEggBlue, label = "Rent"),
//        DonutChartData(300.0, MetallicYellow, label = "Gas"),
//        DonutChartData(700.0, OxfordBlue, label = "Online Purchases"),
//        DonutChartData(300.0, VividOrange, label = "Clothing")
    )
)

@Preview(showBackground = true)
@Composable
fun DonutChartPreview() {
    DonutChart(data = viewData)
}