package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.data.mockCatTransactions
import com.lvrmrc.moneybook.data.mockPeriodTabs
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.presentation.ui.compose.components.ExpensesList
import com.lvrmrc.moneybook.presentation.ui.compose.components.PieChart
import com.lvrmrc.moneybook.presentation.ui.compose.components.tabs.TabItem
import com.lvrmrc.moneybook.presentation.ui.compose.components.tabs.TabsCard
import com.lvrmrc.moneybook.presentation.ui.compose.components.tabs.periodTabs
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme
import com.lvrmrc.moneybook.presentation.viewmodel.ExpenseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun ExpenseScreen(vm: ExpenseViewModel = hiltViewModel()) {
    val expenseScreenScope: CoroutineScope = rememberCoroutineScope()

    SideEffect {
        expenseScreenScope.launch {
            vm.loadTransactions(vm.appState.period)
        }
    }

    ExpenseScreen(
        tabs = periodTabs,
        catTransactions = vm.catTransactions.value,
        tabIndex = vm.periodTabIndex.value,
        total = vm.total,
//        animLaunched = vm.animationLaunched.value,
        setPeriod = {
            expenseScreenScope.launch {
                vm.loadTransactions(it)
            }
        },
//        onAnimLaunched = {
//            vm.setAnimLaunched()
//        }
    )
}

@Composable
private fun ExpenseScreen(
    catTransactions: List<CategoryWithTransactions>,
    tabs: List<TabItem>,
    tabIndex: Int = 0,
    total: Double = 0.0,
    setPeriod: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        TabsCard(tabs, initialPage = tabIndex, currentPage = tabIndex, onTabRowClick = {
            setPeriod(it)
        }, cardContent = {

//            val donutChartData = DonutChartDataList((catTransactions.map {
//                DonutChartData(
//                    it.total, it.color, it.label
//                )
//            }))
//                DonutChart(data = donutChartData, animLaunched = animLaunched, onAnimLaunched = onAnimLaunched)
            PieChart(
                data = catTransactions, text = total.toString()
            )
        })
        ExpensesList(catTransactions)
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseScreenPreview() {
    MoneyBookTheme {
//        AppTabsLayout {
        ExpenseScreen(tabs = mockPeriodTabs, catTransactions = mockCatTransactions)
//        }
    }
}