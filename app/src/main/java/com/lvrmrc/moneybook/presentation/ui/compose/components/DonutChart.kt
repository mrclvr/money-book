package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.data.mockCatTransactions
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.utils.removeDecimal
import com.lvrmrc.moneybook.utils.toFloatPercentage

/**
 * Pie chart with text in chart center
 */
@Composable
fun DonutChart(
    data: List<CategoryWithTransactions>,
    text: String? = null,
    radiusOuter: Dp = 100.dp,
    chartBarWidth: Dp = 35.dp,
) {

    val totalSum = data.sumOf { it.total }
    val angleValues = mutableListOf<Float>()
    var lastAngle = 0f
    val gap = if (data.size < 2) 0f else 1f

    data.forEachIndexed { index, cat ->
        angleValues.add(index, 3.6f * cat.total.toFloatPercentage(totalSum))
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(chartBarWidth), contentAlignment = Alignment.Center
    ) {
        val emptyData = data.isEmpty() || (totalSum == 0.0 && data.size == 1)
        val textString = text ?: if (data.isNotEmpty()) "${totalSum.removeDecimal()}€" else stringResource(R.string.no_transactions)
        val surfaceVariant = colorScheme.surfaceVariant

        // Text container
        Box(
            modifier = Modifier
                .size(radiusOuter * 2f - (chartBarWidth + 8.dp))
                .clip(CircleShape)
                .dashedBorder(surfaceVariant, CircleShape, 2.dp, 4.dp, 6.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                text = textString,
                color = colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = if (emptyData) 16.sp else 24.sp,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }

        Canvas(
            modifier = Modifier.size(radiusOuter * 2f)
        ) {

            if (emptyData) {
                drawArc(
                    color = surfaceVariant,
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
        }
    }
}

fun Modifier.dashedBorder(
    color: Color, shape: Shape, strokeWidth: Dp = 1.dp, dashWidth: Dp = 4.dp, gapWidth: Dp = 4.dp, cap: StrokeCap = StrokeCap.Round
) = this.drawWithContent {
    val outline = shape.createOutline(size, layoutDirection, this)

    val path = Path()
    path.addOutline(outline)

    val stroke = Stroke(
        cap = cap, width = strokeWidth.toPx(), pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashWidth.toPx(), gapWidth.toPx()), phase = 0f
        )
    )

    this.drawContent()

    drawPath(
        path = path, style = stroke, color = color
    )
}

@Composable
@Preview(showBackground = true)
fun DonutChartPreview() {
    NavProvider {
        DonutChart(
            mockCatTransactions
        )
    }
}