package com.lvrmrc.moneybook.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Today
import com.lvrmrc.moneybook.data.entity.Transaction
import com.lvrmrc.moneybook.ui.components.tabs.TabItem
import com.lvrmrc.moneybook.ui.components.PieChart
import java.time.LocalDate

val mockTransactions = listOf(
    Transaction(
        amount = 1.0, title = "Food 1", type = "Expense", date = LocalDate.of(2023, 2, 14)
    ),
    Transaction(
        amount = 2.0, title = "Health 2", type = "Expense", date = LocalDate.of(2023, 8, 25)
    ),
    Transaction(
        amount = 3.0, title = "Cinema 3", type = "Expense", date = LocalDate.of(2023, 11, 22)
    ),
    Transaction(
        amount = 4.0, title = "Food 4", type = "Expense", date = LocalDate.of(2023, 11, 23)
    ),
    Transaction(
        amount = 5.0, title = "Health 5", type = "Expense", date = LocalDate.of(2023, 11, 24)
    ),
    Transaction(
        amount = 6.0, title = "Cinema 6", type = "Expense", date = LocalDate.of(2023, 11, 24)
    ),
)

val mockPeriodTabs = listOf(
    TabItem(title = "Day", icon = Icons.Filled.Today, content = { PieChart("10.0") }),
    TabItem(title = "Month", icon = Icons.Filled.CalendarMonth, content = { PieChart("20.0") }),
    TabItem(title = "Year", icon = Icons.Filled.EditCalendar, content = { PieChart("30.0") })
)
