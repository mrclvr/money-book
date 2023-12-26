package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvrmrc.moneybook.data.mockCatTransactions
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.theme.primary
import com.lvrmrc.moneybook.utils.removeDecimal
import com.lvrmrc.moneybook.utils.toFloatPercentage

/**
 * Pie chart with text in chart center
 */
@Composable
fun PieChart(
    data: List<CategoryWithTransactions>,
    radiusOuter: Dp = 100.dp,
    chartBarWidth: Dp = 35.dp,
    animDuration: Int = 1000,
    text: String? = null,
    animationPlayed: Boolean = false,
    onLoaded: () -> Unit = {}
) {

    val totalSum = data.sumOf { it.total }
    val angleValues = mutableListOf<Float>()

    data.forEachIndexed { index, cat ->
        angleValues.add(index, 3.6f * cat.total.toFloatPercentage(totalSum))
    }

//    val animationPlayed = remember { mutableStateOf(animationPlayed) }

    var lastAngle = 0f
    val gap = if (data.size < 2) 0f else 1f

    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) radiusOuter.value * 2f else 0f, animationSpec = tween(
            durationMillis = animDuration, delayMillis = 0, easing = LinearOutSlowInEasing
        ), label = "Animate size"
    )

    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) 360f * 11f else 0f, animationSpec = tween(
            durationMillis = animDuration, delayMillis = 0, easing = LinearOutSlowInEasing
        ), label = "Animate rotation"
    )

    LaunchedEffect(key1 = true) {
        onLoaded()
    }

    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {

        Box(
//            modifier = Modifier.size(animateSize.dp),
//                .size(radiusOuter * 2f + chartBarWidth)
            contentAlignment = Alignment.Center
        ) {
            val textMeasurer = rememberTextMeasurer()

            Canvas(
                modifier = Modifier.size(radiusOuter * 2f)
//                    .rotate(animateRotation)
            ) {

                if (data.isEmpty() || (totalSum == 0.0 && data.size == 1)) {
                    drawArc(
                        color = primary,
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                    )
                } else {
                    angleValues.forEachIndexed { idx, angle ->
                        if (angle > 0) {
                            drawArc(
                                color = data[idx].color,
                                startAngle = lastAngle + gap,
                                sweepAngle = angle - 2 * gap,
                                useCenter = false,
                                style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                            )
                            lastAngle += angle
                        }
                    }
                }
                val textString = text ?: if (data.isNotEmpty()) "${totalSum.removeDecimal()}€" else ""
                val annotatedString = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold, fontSize = 24.sp
                        )
                    ) {
                        append(textString)
                    }
                }
//                val maxSize: Int = (radiusOuter * 2f - chartBarWidth / 2f).toPx().toInt()
                val textLayoutResult: TextLayoutResult = textMeasurer.measure(
                    annotatedString,
//                    constraints = Constraints(maxWidth = maxSize)
                )
                val textSize = textLayoutResult.size

                drawText(
                    textMeasurer = textMeasurer,
                    text = annotatedString,
                    topLeft = Offset(
                        (size.width - textSize.width) / 2f, (size.height - textSize.height) / 2f
                    ),
                )


            }
        }

        // To see the data in more structured way
        // Compose Function in which Items are showing data
//        DetailsPieChart(
//            data = data, colors = colors
//        )

    }

}

@Composable
fun DetailsPieChart(
    data: Map<String, Int>, colors: List<Color>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp)
    ) {
        data.values.forEachIndexed { index, value ->
            DetailsPieChartItem(
                data = Pair(data.keys.elementAt(index), value), color = colors[index]
            )
        }

    }
}

@Composable
fun DetailsPieChartItem(
    data: Pair<String, Int>, height: Dp = 45.dp, color: Color
) {

    Surface(
        modifier = Modifier.padding(vertical = 10.dp, horizontal = 40.dp), color = Color.Transparent
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .background(
                        color = color, shape = RoundedCornerShape(10.dp)
                    )
                    .size(height)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = data.first,
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = data.second.toString(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp,
                    color = Color.Gray
                )
            }

        }

    }

}

@Composable
@Preview(showBackground = true)
fun PieChartPreview() {
    NavProvider {
        PieChart(
            mockCatTransactions
        )
    }
}