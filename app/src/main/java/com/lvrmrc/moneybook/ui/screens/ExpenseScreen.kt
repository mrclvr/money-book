package com.lvrmrc.moneybook.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.components.ExpensesList
import com.lvrmrc.moneybook.ui.components.tabs.TimeTabsCard
import com.lvrmrc.moneybook.ui.layouts.BottomBarLayout
import com.lvrmrc.moneybook.viewmodels.ExpenseViewModel


@Composable
fun ExpenseScreen(viewModel: ExpenseViewModel = hiltViewModel(), navController: NavHostController) {
    ExpenseScreen(navController = navController, viewModel)
}


@Composable
private fun ExpenseScreen(navController: NavHostController, viewModel: ExpenseViewModel) {
    var inputAmount by remember { mutableStateOf("") }

    val total = viewModel.total.value
    val transactions = viewModel.transactions.value

    BottomBarLayout(navController, viewModel) {
        Box {
            Column(verticalArrangement = Arrangement.spacedBy(25.dp)) {
                TimeTabsCard(viewModel)

                ExpensesList(transactions)

            }
        }
    }
}

@Preview
@Composable
private fun ExpenseScreenPreview() {
    ExpenseScreen(navController = rememberNavController(), hiltViewModel())
}