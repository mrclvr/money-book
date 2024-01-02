package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.data.mockCatTransactions
import com.lvrmrc.moneybook.data.periodTabs
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.model.transTypeIntMap
import com.lvrmrc.moneybook.presentation.ui.compose.components.DateDirection
import com.lvrmrc.moneybook.presentation.ui.compose.components.TabsCard
import com.lvrmrc.moneybook.presentation.ui.compose.components.TransactionsByCategoryList
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.TabsLayout
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.navigateDefault
import com.lvrmrc.moneybook.presentation.viewmodel.AppViewModel
import com.lvrmrc.moneybook.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Composable
fun HomeScreen(
    appVm: AppViewModel = hiltViewModel(),
    vm: HomeViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = appVm.period, key2 = appVm.transType, key3 = appVm.date) {
        CoroutineScope(Dispatchers.IO).launch {
            vm.loadTransactions(appVm.period, appVm.transType, appVm.date)
        }
    }

    HomeScreen(
        period = appVm.period,
        currentDate = appVm.date,
        tabIndex = transTypeIntMap[appVm.transType] ?: 0,
        transactionsByCategory = vm.catTransactions.value,
        onSetPeriod = {
            appVm.setDate(LocalDateTime.now())
            appVm.setPeriod(it)
        },
        onSetTransType = { appVm.setTransType(it) },
        onDateBack = { appVm.shiftDate(DateDirection.MINUS) },
        onDateForward = { appVm.shiftDate(DateDirection.PLUS) },
//        animationPlayed = appVm.pieAnimationPlayed,
//        setAnimationPlayed = { appVm.setPieAnimationPlayed(true) }
    )
}

@Composable
private fun HomeScreen(
    period: TransactionPeriod,
    currentDate: LocalDateTime,
    tabIndex: Int,
    transactionsByCategory: List<CategoryWithTransactions>,
    onSetPeriod: (TransactionPeriod) -> Unit = {},
    onSetTransType: (TransactionType) -> Unit = {},
    onDateBack: () -> Unit = {},
    onDateForward: () -> Unit = {},
//    animationPlayed: Boolean,
//    setAnimationPlayed: () -> Unit = {}
) {
    val navController = LocalNavController.current

    TabsLayout(index = tabIndex, onNavClick = {
        onSetTransType(it)
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 8.dp), verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TabsCard(periodTabs, transactionsByCategory, period, currentDate, onSetPeriod = {
                onSetPeriod(it)
            }, onDateBack = onDateBack, onDateForward = onDateForward)

            TransactionsByCategoryList(catTransactions = transactionsByCategory, onSetCategory = {
                navController.navigateDefault("${Screen.CategoryTransactions.route}/${it.id}")
            })
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    NavProvider {
        HomeScreen(
            period = TransactionPeriod.DAY, currentDate = LocalDateTime.now(), tabIndex = 0, transactionsByCategory = mockCatTransactions
        )
    }
}