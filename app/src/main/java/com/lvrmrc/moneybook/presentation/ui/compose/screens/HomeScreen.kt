package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.data.mockCatTransactions
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.model.periodTabs
import com.lvrmrc.moneybook.domain.model.transTypeIntMap
import com.lvrmrc.moneybook.presentation.ui.compose.components.ExpensesList
import com.lvrmrc.moneybook.presentation.ui.compose.components.PieChart
import com.lvrmrc.moneybook.presentation.ui.compose.components.TabsCard
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.TabsLayout
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.navigateDefault
import com.lvrmrc.moneybook.presentation.viewmodel.AppViewModel
import com.lvrmrc.moneybook.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    appVm: AppViewModel = hiltViewModel(),
    vm: HomeViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = appVm.period, key2 = appVm.transType) {
        CoroutineScope(Dispatchers.IO).launch {
            vm.loadTransactions(appVm.period, appVm.transType)
        }
    }

    HomeScreen(period = appVm.period,
        tabIndex = transTypeIntMap[appVm.transType] ?: 0,
        catTransactions = vm.catTransactions.value,
        animationPlayed = appVm.pieAnimationPlayed,
        onSetPeriod = { appVm.setPeriod(it) },
        onSetTransType = { appVm.setTransType(it) },
        setAnimationPlayed = { appVm.setPieAnimationPlayed(true) })
}

@Composable
private fun HomeScreen(
    period: TransactionPeriod,
    tabIndex: Int,
    catTransactions: List<CategoryWithTransactions>,
    animationPlayed: Boolean,
    onSetPeriod: (TransactionPeriod) -> Unit = {},
    onSetTransType: (TransactionType) -> Unit = {},
    setAnimationPlayed: () -> Unit = {}
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
                if (catTransactions.isNotEmpty()) {
                    PieChart(data = catTransactions, animationPlayed = animationPlayed, onLoaded = { setAnimationPlayed() })
                } else {
                    Text(stringResource(R.string.no_transactions))
                }
            })
            ExpensesList(catTransactions, onSetCategory = {
                navController.navigateDefault("${Screen.CategoryDetails.route}/${it.id}")
            })
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    NavProvider {
        HomeScreen(TransactionPeriod.DAY, 0, mockCatTransactions, false)
    }
}