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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.data.AppState
import com.lvrmrc.moneybook.data.mockCatTransactions
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.transTypeIntMap
import com.lvrmrc.moneybook.presentation.ui.compose.components.ExpensesList
import com.lvrmrc.moneybook.presentation.ui.compose.components.PieChart
import com.lvrmrc.moneybook.presentation.ui.compose.components.tabs.TabsCard
import com.lvrmrc.moneybook.presentation.ui.compose.components.tabs.periodTabs
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.TabsLayout
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme
import com.lvrmrc.moneybook.presentation.viewmodel.ExpenseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ExpenseScreen(
    navController: NavHostController = rememberNavController(),
    vm: ExpenseViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = vm.appState.period, key2 = vm.appState.transType) {
        CoroutineScope(Dispatchers.IO).launch {
            vm.loadTransactions()
        }
    }

    ExpenseScreen(navController = navController, catTransactions = vm.catTransactions.value,
//        animLaunched = vm.animationLaunched.value,
        onSetCategory = {
            vm.setCategory(it)
        })
}

@Composable
private fun ExpenseScreen(
    navController: NavHostController = rememberNavController(),
    catTransactions: List<CategoryWithTransactions>,
    onSetCategory: (CategoryWithTransactions) -> Unit = {}
) {
    val appState = AppState.getInstance()

    TabsLayout(navController = navController, index = transTypeIntMap[appState.transType] ?: 0, onNavClick = {
        appState.setTransType(it)
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background)
                .padding(25.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TabsCard(periodTabs, appState.period, onSetPeriod = {
                appState.setPeriod(it)
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
    MoneyBookTheme {
//        AppTabsLayout {
        ExpenseScreen(catTransactions = mockCatTransactions)
//        }
    }
}