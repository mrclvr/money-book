package com.lvrmrc.moneybook.ui.components.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Today
import com.lvrmrc.moneybook.ui.screens.tabs.PieChartTab
import com.lvrmrc.moneybook.viewmodels.ExpenseViewModel

fun transactionPeriodTabs(expenseViewModel: ExpenseViewModel): List<TabItem> {
    return listOf(TabItem(title = "Day", icon = Icons.Filled.Today, content = { PieChartTab(expenseViewModel) }) {
        expenseViewModel.getByPeriod("day")
    }, TabItem(title = "Month", icon = Icons.Filled.CalendarMonth, content = { PieChartTab(expenseViewModel) }) {
        expenseViewModel.getByPeriod("month")
    }, TabItem(title = "Year", icon = Icons.Filled.EditCalendar, content = { PieChartTab(expenseViewModel) }) {
        expenseViewModel.getByPeriod("year")
    })
}
