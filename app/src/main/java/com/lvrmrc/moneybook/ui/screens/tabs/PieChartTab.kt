package com.lvrmrc.moneybook.ui.screens.tabs;

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.lvrmrc.moneybook.viewmodels.ExpenseViewModel

@Composable
fun PieChartTab(expenseViewModel: ExpenseViewModel) {
    Text(expenseViewModel.total.value.toString(), fontSize = 30.sp)
}
