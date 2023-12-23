package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.data.mockCatTransactions
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.model.transTypeIntMap
import com.lvrmrc.moneybook.presentation.ui.compose.components.ExpensesList
import com.lvrmrc.moneybook.presentation.ui.compose.components.PieChart
import com.lvrmrc.moneybook.presentation.ui.compose.components.tabs.TabsCard
import com.lvrmrc.moneybook.presentation.ui.compose.components.tabs.periodTabs
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.TabsLayout
import com.lvrmrc.moneybook.presentation.viewmodel.AppViewModel
import com.lvrmrc.moneybook.presentation.viewmodel.ExpenseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ExpenseScreen(
    appVm: AppViewModel = hiltViewModel(),
    vm: ExpenseViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = appVm.period, key2 = appVm.transType) {
        CoroutineScope(Dispatchers.IO).launch {
            vm.loadTransactions(appVm.period, appVm.transType)
        }
    }

    ExpenseScreen(period = appVm.period,
        tabIndex = transTypeIntMap[appVm.transType] ?: 0,
        catTransactions = vm.catTransactions.value,
        onSetCategory = {
            vm.setCategory(it)
        },
        onSetPeriod = { appVm.setPeriod(it) },
        onSetTransType = { appVm.setTransType(it) })
}

@Composable
private fun ExpenseScreen(
    period: TransactionPeriod,
    tabIndex: Int,
    catTransactions: List<CategoryWithTransactions>,
    onSetCategory: (CategoryWithTransactions) -> Unit = {},
    onSetPeriod: (TransactionPeriod) -> Unit = {},
    onSetTransType: (TransactionType) -> Unit = {}
) {
    val navController = LocalNavController.current

    TabsLayout(index = tabIndex, onNavClick = {
        onSetTransType(it)
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background)
                .padding(25.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TabsCard(periodTabs, period, onSetPeriod = {
                onSetPeriod(it)
            }, cardContent = {

//            val donutChartData = DonutChartDataList((catTransactions.map {
//                DonutChartData(
//                    it.total, it.color, it.label
//                )
//            }))
//                DonutChart(data = donutChartData, animLaunched = animLaunched, onAnimLaunched = onAnimLaunched)
                PieChart(
                    data = catTransactions
                )
            })
            ExpensesList(catTransactions, onSetCategory = {
                onSetCategory(it)
                navController.navigate("${Screen.CategoryDetails.route}/${it.id}") {

                    navController.graph.route?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ExpenseScreenPreview() {
    NavProvider {
//        AppTabsLayout {
        ExpenseScreen(TransactionPeriod.DAY, 0, mockCatTransactions)
//        }
    }
}