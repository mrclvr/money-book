package com.lvrmrc.moneybook.presentation.ui.compose.components.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Today
import com.lvrmrc.moneybook.domain.model.TransactionType


val periodTabs: List<TabItem> = listOf(
    TabItem(title = "Day", icon = Icons.Filled.Today),
    TabItem(title = "Month", icon = Icons.Filled.CalendarMonth),
    TabItem(title = "Year", icon = Icons.Filled.EditCalendar)
)

val transactionsTabs: List<TabItem> = listOf(
    TabItem(title = "Expense", icon = Icons.Filled.ArrowUpward, type = TransactionType.EXPENSE),
    TabItem(title = "Income", icon = Icons.Filled.ArrowDownward, type = TransactionType.INCOME),
)


