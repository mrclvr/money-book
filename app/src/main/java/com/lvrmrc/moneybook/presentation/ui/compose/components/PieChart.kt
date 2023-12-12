package com.lvrmrc.moneybook.presentation.ui.compose.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvrmrc.moneybook.data.mockCatTransactions
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme

@Composable
fun PieChart(
    data: List<CategoryWithTransactions>,
    radiusOuter: Dp = 100.dp,
    chartBarWidth: Dp = 35.dp,
    animDuration: Int = 1000,
    text: String = "Text"
) {

    val totalSum = data.sumOf { it.total }
    val angleValues = mutableListOf<Float>()

    // To set the value of each Arc according to
    // the value given in the data, we have used a simple formula.
    // For a detailed explanation check out the Medium Article.
    // The link is in the about section and readme file of this GitHub Repository
    data.forEachIndexed { index, cat ->
        angleValues.add(index, 360 * cat.transactions.sumOf { it.amount }.toFloat() / totalSum.toFloat())
    }

    // add the colors as per the number of data(no. of pie chart entries)
    // so that each data will get a color
//    val colors = listOf(
//        Purple200, Purple500, Teal200, Purple700, Blue
//    )

//    val animationPlayed = remember { mutableStateOf(false) }

    var lastAngle = 0f
    val gap = 1f

    // it is the diameter value of the Pie
//    val animateSize by animateFloatAsState(
//        targetValue = if (animationPlayed.value) radiusOuter.value * 2f else 0f, animationSpec = tween(
//            durationMillis = animDuration, delayMillis = 0, easing = LinearOutSlowInEasing
//        ), label = "Animate size"
//    )

    // if you want to stabilize the Pie Chart you can use value -90f
    // 90f is used to complete 1/4 of the rotation
//    val animateRotation by animateFloatAsState(
//        targetValue = if (animationPlayed.value) 90f * 11f else 0f, animationSpec = tween(
//            durationMillis = animDuration, delayMillis = 0, easing = LinearOutSlowInEasing
//        ), label = "Animate rotation"
//    )

    // to play the animation only once when the function is Created or Recomposed
//    LaunchedEffect(key1 = true) {
//        animationPlayed.value = true
//    }

    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {

        Box(
//            modifier = Modifier.size(animateSize.dp),
            contentAlignment = Alignment.Center
        ) {
            val textMeasurer = rememberTextMeasurer()

            Canvas(
                modifier = Modifier.size(radiusOuter * 2f)
//                    .rotate(animateRotation)
            ) {
                angleValues.forEachIndexed { idx, angle ->
                    drawArc(
                        color = data[idx].color,
                        startAngle = lastAngle + gap,
                        sweepAngle = angle - 2 * gap,
                        useCenter = false,
                        style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                    )
                    lastAngle += angle
                }

                val canvasWidth = size.width
                val canvasHeight = size.height
                val annotatedString = buildAnnotatedString {
                    append(text)
//                    addStyle(SpanStyle(fontSize = 50.sp), text.length, this.length)
                    toAnnotatedString()
                }
                val textLayoutResult: TextLayoutResult = textMeasurer.measure(annotatedString)

                val textSize = textLayoutResult.size

                drawText(
//                    style = TextStyle(fontSize = 30.sp),
                    textMeasurer = textMeasurer,
                    text = text,
                    topLeft = Offset(
                        (canvasWidth - textSize.width) / 2f, (canvasHeight - textSize.height) / 2f
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
            .padding(top = 80.dp)
            .fillMaxWidth()
    ) {
        // create the data items
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
@Preview
fun PieChartPreview() {
    MoneyBookTheme {
//        ComposeLocalWrapper {
//        AppTabsLayout {
        PieChart(
            mockCatTransactions
        )
//        }
//        }
    }
}