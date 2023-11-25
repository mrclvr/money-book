package com.lvrmrc.moneybook.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.layouts.TopBarLayout
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme

@Composable
fun TransactionScreen(navController: NavHostController) {
    var inputAmount by remember { mutableStateOf("") }

    TopBarLayout(navController, Screen.Transaction.label, content = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            TextField(value = inputAmount, onValueChange = { value ->
                inputAmount = value.filter { it.isDigit() }
            })

        }
    })

//    val coroutineScope = rememberCoroutineScope()

//                    coroutineScope.launch {
//                        viewModel.addExpense(
//                            Transaction(
//                                amount = 5.55, title = "Pippo", type = "Expense"
//                            )
//                        )
//
//                    }
}

@Preview
@Composable
private fun TransactionScreenPreview(
) {
    MoneyBookTheme {
        TransactionScreen(rememberNavController())
    }
}