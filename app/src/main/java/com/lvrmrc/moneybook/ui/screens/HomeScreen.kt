package com.lvrmrc.moneybook.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.components.ExpensesList
import com.lvrmrc.moneybook.ui.components.tabs.TabsCard
import com.lvrmrc.moneybook.ui.layouts.BottomBarLayout
import com.lvrmrc.moneybook.viewmodels.HomeViewModel


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavHostController) {
    HomeScreen(navController = navController, viewModel)
}

@Composable
private fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel) {
    var inputAmount by remember { mutableStateOf("") }

    val total = viewModel.total.value
    val transactions = viewModel.transactions.value

    BottomBarLayout(navController) {
        Box {
            Column(verticalArrangement = Arrangement.spacedBy(25.dp)) {
                TabsCard(
                    300.dp
                ) {
                    Row {
                        Text(text = "Saldo")
                        Spacer(modifier = Modifier.size(15.dp))
                        Text(text = total.toString() ?: "0", modifier = Modifier.clickable {
                            navController.navigate(route = Screen.Home.route)
                        })
                    }
                    TextField(value = inputAmount, onValueChange = { value ->
                        inputAmount = value.filter { it.isDigit() }
                    })
                }

                ExpensesList(transactions)

            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController(), hiltViewModel())
}