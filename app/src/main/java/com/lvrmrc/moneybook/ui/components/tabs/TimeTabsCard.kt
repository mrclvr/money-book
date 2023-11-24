package com.lvrmrc.moneybook.ui.components.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.ui.screens.tabs.PieChartTab
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme
import com.lvrmrc.moneybook.viewmodels.ExpenseViewModel
import kotlinx.coroutines.launch

@Composable
fun TimeTabsCard(viewModel: ExpenseViewModel) {
    val coroutineScope = rememberCoroutineScope()

    val timeTabs = listOf(TabItem(title = "Day", icon = Icons.Filled.Today, content = { PieChartTab() }, onClick = {
        coroutineScope.launch {
            viewModel.getByPeriod("day")
        }
    }), TabItem(title = "Month", icon = Icons.Filled.CalendarMonth, content = { Text(text = "Month") }, onClick = {
        coroutineScope.launch {
            viewModel.getByPeriod("month")
        }
    }), TabItem(title = "Year", icon = Icons.Filled.EditCalendar, content = { Text(text = "Year") }, onClick = {
        coroutineScope.launch {
            viewModel.getByPeriod("year")
        }
    })
    )

    TabsCard(
        400.dp, timeTabs
    )

}

@Preview
@Composable
private fun TimeTabsCardPreview() {
    MoneyBookTheme {
        TimeTabsCard(
            hiltViewModel()
        )
    }
}