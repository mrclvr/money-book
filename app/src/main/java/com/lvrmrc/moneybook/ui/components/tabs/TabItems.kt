package com.lvrmrc.moneybook.ui.components.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Today
import com.lvrmrc.moneybook.ui.components.PieChart
import com.lvrmrc.moneybook.viewmodels.ExpenseViewModel

fun transactionPeriodTabs(expenseViewModel: ExpenseViewModel): List<TabItem> {
    val total = expenseViewModel.total.value.toString()

    return listOf(TabItem(title = "Day", icon = Icons.Filled.Today, content = { PieChart(total) }) {
        expenseViewModel.getByPeriod("day")
    }, TabItem(title = "Month", icon = Icons.Filled.CalendarMonth, content = { PieChart(total) }) {
        expenseViewModel.getByPeriod("month")
    }, TabItem(title = "Year", icon = Icons.Filled.EditCalendar, content = { PieChart(total) }) {
        expenseViewModel.getByPeriod("year")
    })
}
