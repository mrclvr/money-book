package com.lvrmrc.moneybook.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.ui.components.ExpensesList
import com.lvrmrc.moneybook.ui.components.tabs.TimeTabsCard
import com.lvrmrc.moneybook.ui.layouts.BottomBarLayout
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme
import com.lvrmrc.moneybook.viewmodels.ExpenseViewModel


//@Composable
//fun ExpenseScreen(viewModel: ExpenseViewModel = hiltViewModel(), navController: NavHostController) {
//    ExpenseScreen(navController = navController, viewModel)
//}


@Composable
fun ExpenseScreen(viewModel: ExpenseViewModel = hiltViewModel()) {

    val transactions = viewModel.transactions.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        TimeTabsCard(viewModel)
        ExpensesList(transactions)
    }
}

@Preview(showBackground = true)
@Composable
fun ExpenseScreenPreview() {
    MoneyBookTheme {
        BottomBarLayout() {
            ExpenseScreen()
        }
    }
}