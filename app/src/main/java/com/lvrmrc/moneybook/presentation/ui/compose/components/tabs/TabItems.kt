package com.lvrmrc.moneybook.presentation.ui.compose.components.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Today
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen

//fun transactionPeriodTabs(vm: ExpenseViewModel): List<TabItem> {
//    val total = vm.total.value.toString()
//
//    return listOf(TabItem(title = "Day", icon = Icons.Filled.Today, content = { PieChart(total) }) {
//        vm.getByPeriod("day")
//    }, TabItem(title = "Month", icon = Icons.Filled.CalendarMonth, content = { PieChart(total) }) {
//        vm.getByPeriod("month")
//    }, TabItem(title = "Year", icon = Icons.Filled.EditCalendar, content = { PieChart(total) }) {
//        vm.getByPeriod("year")
//    })
//}

val periodTabs: List<TabItem> = listOf(
    TabItem(title = "Day", icon = Icons.Filled.Today),
    TabItem(title = "Month", icon = Icons.Filled.CalendarMonth),
    TabItem(title = "Year", icon = Icons.Filled.EditCalendar)
)

//val screenTabs2 = listOf(
//    ScreenTabItem(screen = Screen.Expense, content = { ExpenseScreen() }),
//    ScreenTabItem(screen = Screen.Income, content = { IncomeScreen() }),
//)
val transactionsTabs: List<TabItem> = listOf(
    TabItem(title = Screen.Expense.label, icon = Icons.Filled.ArrowUpward, type = TransactionType.EXPENSE),
    TabItem(title = Screen.Income.label, icon = Icons.Filled.ArrowDownward, type = TransactionType.INCOME),
)


