package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.data.mockCatTransactions
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.domain.model.TransactionWithCategory
import com.lvrmrc.moneybook.presentation.ui.compose.components.TransactionsListItem
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.AppLayout
import com.lvrmrc.moneybook.presentation.viewmodel.ExpenseViewModel

@Composable
fun TransactionsDetailsScreen(
    navController: NavHostController = rememberNavController(), vm: ExpenseViewModel = hiltViewModel()
) {
    TransactionsDetailsScreen(
        navController = navController,
        category = vm.selectedCategory.value,
//        onSetTransaction = { vm.appState.setCurrentTransaction(it) }
    )
}

@Composable
private fun TransactionsDetailsScreen(
    navController: NavHostController = rememberNavController(),
    category: CategoryWithTransactions?,
    onSetTransaction: (TransactionWithCategory) -> Unit = {}
) {

    if (category != null) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()

        ) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = category.label,
                    textAlign = TextAlign.Center,
                    color = colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }

            items(category.transactions.size) { idx ->

                val transaction: Transaction = category.transactions[idx]
                val cat = category.toCategory()

                TransactionsListItem(transaction, cat) {
                    onSetTransaction(transaction.toTransactionWithCategory(cat))

                    navController.navigate("${Screen.Transaction.route}?transactionId=${transaction.id}") {

                        navController.graph.route?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TransactionsDetailsScreenPreview(
) {
    AppLayout {
        TransactionsDetailsScreen(category = mockCatTransactions[0])
    }
}