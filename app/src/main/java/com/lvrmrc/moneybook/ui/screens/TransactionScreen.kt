package com.lvrmrc.moneybook.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.layouts.TopBarLayout
import com.lvrmrc.moneybook.viewmodels.TransactionViewModel

//@Composable
//fun TransactionScreen(
//    navController: NavHostController, viewModel: TransactionViewModel = hiltViewModel(),
//) {
//    TransactionScreen(navController, viewModel)
//}

@Composable
fun TransactionScreen(navController: NavHostController, viewModel: TransactionViewModel = hiltViewModel()) {
    TopBarLayout(navController, Screen.Transaction.label, content = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            Row {
//                Text(text = "Saldo")
//                Spacer(modifier = Modifier.size(15.dp))
//                Text(text = total.toString() ?: "0", modifier = Modifier.clickable {
//                    navController.navigate(route = Screen.Home.route)
//                })
//            }
//            TextField(value = inputAmount, onValueChange = { value ->
//                inputAmount = value.filter { it.isDigit() }
//            })

        }
    })
}

@Preview
@Composable
private fun TransactionScreenPreview(
//    @PreviewParameter(TransactionScreenPreviewParamProvider::class) transactions: List<Transaction>
) {
    TransactionScreen(navController = rememberNavController(), viewModel = hiltViewModel())
}

//private class TransactionScreenPreviewParamProvider : PreviewParameterProvider<List<Transaction>> {
//
//    override val values: Sequence<List<Transaction>> = sequenceOf(
//        listOf(
//            Transaction(
//                amount = 5.55, title = "Food",
//                , type = "Expense"
//            ),
//            Transaction(
//                amount = 5.55, title = "Health",
//                , type = "Expense"
//            ),
//            Transaction(
//                amount = 5.55, title = "Cinema",
//                , type = "Expense"
//            )
//        )
//    )
//}