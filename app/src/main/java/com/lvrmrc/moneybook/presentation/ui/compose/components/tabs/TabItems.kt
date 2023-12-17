package com.lvrmrc.moneybook.presentation.ui.compose.components.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Today
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType


val periodTabs: List<PeriodTabItem> = listOf(
    PeriodTabItem(title = "Day", icon = Icons.Filled.Today, period = TransactionPeriod.DAY),
    PeriodTabItem(title = "Month", icon = Icons.Filled.CalendarMonth, period = TransactionPeriod.MONTH),
    PeriodTabItem(title = "Year", icon = Icons.Filled.EditCalendar, period = TransactionPeriod.YEAR)
)

val transactionsTabs: List<TransTypeTabItem> = listOf(
    TransTypeTabItem(title = "Expense", icon = Icons.Filled.ArrowUpward, type = TransactionType.EXPENSE),
    TransTypeTabItem(title = "Income", icon = Icons.Filled.ArrowDownward, type = TransactionType.INCOME),
)


