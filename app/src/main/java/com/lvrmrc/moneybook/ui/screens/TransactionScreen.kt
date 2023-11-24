package com.lvrmrc.moneybook.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.layouts.TopBarLayout
import com.lvrmrc.moneybook.viewmodels.ExpenseViewModel

//@Composable
//fun TransactionScreen(
//    navController: NavHostController, viewModel: TransactionViewModel = hiltViewModel(),
//) {
//    TransactionScreen(navController, viewModel)
//}

@Composable
fun TransactionScreen(navController: NavHostController, viewModel: ExpenseViewModel = hiltViewModel()) {
    var inputAmount by remember { mutableStateOf("") }

    TopBarLayout(navController, Screen.Transaction.label, content = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row {
                Text(text = "FROM:  ${navController.previousBackStackEntry?.destination?.route}")
                Spacer(modifier = Modifier.size(15.dp))
            }
            TextField(value = inputAmount, onValueChange = { value ->
                inputAmount = value.filter { it.isDigit() }
            })

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