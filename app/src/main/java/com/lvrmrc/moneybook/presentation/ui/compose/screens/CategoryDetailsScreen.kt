package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.data.mockCategories
import com.lvrmrc.moneybook.data.mockTransactions
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.domain.model.TransactionWithCategory
import com.lvrmrc.moneybook.presentation.ui.compose.components.TransactionsListItem
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.AppLayout
import com.lvrmrc.moneybook.presentation.viewmodel.CategoryDetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CategoryDetailsScreen(
    navController: NavHostController = rememberNavController(), vm: CategoryDetailsViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        CoroutineScope(Dispatchers.IO).launch {
            vm.initCategory()
        }
    }
    
    CategoryDetailsScreen(
        navController = navController,
        category = vm.category,
        transactions = vm.transactions,
//        onSetTransaction = { vm.appState.setCurrentTransaction(it) }
    )
}

@Composable
private fun CategoryDetailsScreen(
    category: Category?,
    transactions: List<Transaction>,
    onSetTransaction: (TransactionWithCategory) -> Unit = {},
    navController: NavHostController = rememberNavController(),
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

            items(transactions.size) { idx ->

                val transaction: Transaction = transactions[idx]

                TransactionsListItem(transaction, category) {
                    onSetTransaction(transaction.toTransactionWithCategory(category))

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
        CategoryDetailsScreen(mockCategories[0], mockTransactions)
    }
}