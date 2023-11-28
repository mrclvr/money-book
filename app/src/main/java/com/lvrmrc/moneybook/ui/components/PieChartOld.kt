package com.lvrmrc.moneybook.ui.components;

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme

@Composable
fun PieChartOld(double: Double) {
    Text(text = double.toString(), fontSize = 30.sp)
}

@Preview(showBackground = true)
@Composable
private fun PieChartOldPreview() {
    MoneyBookTheme {
        PieChartOld(25.0)
    }
}
