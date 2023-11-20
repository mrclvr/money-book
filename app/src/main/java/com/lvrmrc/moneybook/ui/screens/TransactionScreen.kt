package com.lvrmrc.moneybook.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.data.entity.Transaction
import com.lvrmrc.moneybook.ui.components.TopBarLayout
import com.lvrmrc.moneybook.viewmodels.TransactionViewModel

@Composable
fun TransactionScreen(
    viewModel: TransactionViewModel = hiltViewModel(), navController: NavHostController
) {
    TransactionScreen(navController = navController, transactions = viewModel.transactions)
}

@Composable
private fun TransactionScreen(navController: NavHostController, transactions: List<Transaction>) {
    TopBarLayout(navController, Screen.Transaction.label, content = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column {
                transactions.forEach { t ->
                    Text(text = t.notes, modifier = Modifier.clickable { navController.navigate(route = Screen.Transaction.route) })
                }
            }

        }
    })
}

@Preview
@Composable
private fun TransactionScreenPreview(
    @PreviewParameter(TransactionScreenPreviewParamProvider::class) transactions: List<Transaction>
) {
    TransactionScreen(navController = rememberNavController(), transactions)
}

private class TransactionScreenPreviewParamProvider : PreviewParameterProvider<List<Transaction>> {

    override val values: Sequence<List<Transaction>> = sequenceOf(
        listOf(
            Transaction(id = 123, amount = 5.55, notes = "Food", timestamp = 0),
            Transaction(id = 345, amount = 5.55, notes = "Health", timestamp = 1),
            Transaction(id = 999, amount = 5.55, notes = "Cinema", timestamp = 2)
        )
    )
}