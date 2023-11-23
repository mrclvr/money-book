package com.lvrmrc.moneybook.ui.components.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Text
import com.lvrmrc.moneybook.ui.screens.tabs.PieChartTab

val tabs = listOf(
    TabItem(title = "Day", icon = Icons.Filled.Today, content = { PieChartTab() }),
    TabItem(title = "Month", icon = Icons.Filled.CalendarMonth, content = { Text(text = "Month") }),
    TabItem(title = "Year", icon = Icons.Filled.EditCalendar, content = { Text(text = "Year") })
)