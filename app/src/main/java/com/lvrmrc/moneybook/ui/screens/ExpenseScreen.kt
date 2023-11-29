package com.lvrmrc.moneybook.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.data.entity.Transaction
import com.lvrmrc.moneybook.data.entity.TransactionType
import com.lvrmrc.moneybook.data.mockPeriodTabs
import com.lvrmrc.moneybook.data.mockTransactions
import com.lvrmrc.moneybook.ui.components.DonutChart
import com.lvrmrc.moneybook.ui.components.ExpensesList
import com.lvrmrc.moneybook.ui.components.tabs.TabItem
import com.lvrmrc.moneybook.ui.components.tabs.TabsCard
import com.lvrmrc.moneybook.ui.components.tabs.periodTabs
import com.lvrmrc.moneybook.ui.components.viewData
import com.lvrmrc.moneybook.ui.layouts.AppLayout
import com.lvrmrc.moneybook.ui.layouts.TabsLayout
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme
import com.lvrmrc.moneybook.viewmodels.ExpenseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun ExpenseScreen(vm: ExpenseViewModel = hiltViewModel()) {
    val expenseScreenScope: CoroutineScope = rememberCoroutineScope()

//    LaunchedEffect(Unit) {
//        vm.setTransType(type)
//        vm.loadTransactions()
//    }
    ExpenseScreen(tabs = periodTabs,
        transactions = vm.transactions.value,
        tabIndex = vm.periodTabIndex.value,
        total = vm.total,
        onTabRowClick = {
            expenseScreenScope.launch {
                vm.getByPeriod(it)
            }
        },
        onLayoutNavClick = {
            vm.setTransType(it)
        })
}

@Composable
private fun ExpenseScreen(
    transactions: List<Transaction>,
    tabs: List<TabItem>,
    tabIndex: Int = 0,
    total: Double = 0.0,
    onTabRowClick: (String) -> Unit = {},
    onLayoutNavClick: (TransactionType) -> Unit = {}
) {

    TabsLayout(onLayoutNavClick = { onLayoutNavClick(it) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
                .background(Color.Transparent),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TabsCard(tabs, initialPage = tabIndex, currentPage = tabIndex, onTabRowClick = {
                onTabRowClick(it)
            }, cardContent = {
                DonutChart(data = viewData)
//                PieChart(
//                    data = mapOf(
//                        Pair("Sample-1", 150),
//                        Pair("Sample-2", 120),
//                        Pair("Sample-3", 110),
//                        Pair("Sample-4", 170),
//                        Pair("Sample-5", 120),
//                    ), text = total.toString()
//                )
            })
            ExpensesList(transactions)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseScreenPreview() {
    MoneyBookTheme {
        AppLayout {
            ExpenseScreen(mockTransactions, mockPeriodTabs)
        }
    }
}