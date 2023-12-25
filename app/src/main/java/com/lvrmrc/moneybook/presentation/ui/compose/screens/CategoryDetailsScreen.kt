package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.data.expenseCategories
import com.lvrmrc.moneybook.data.mockTransactions
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.ScreenHeader
import com.lvrmrc.moneybook.presentation.ui.compose.components.TransactionsListItem
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.AppLayout
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.viewmodel.AppViewModel
import com.lvrmrc.moneybook.presentation.viewmodel.CategoryDetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun CategoryDetailsScreen(
    appVm: AppViewModel = hiltViewModel(), vm: CategoryDetailsViewModel = hiltViewModel()
) {

    LaunchedEffect(appVm.period, appVm.transType) {
        CoroutineScope(Dispatchers.IO).launch {
            vm.loadCategoryTransactions(appVm.period, appVm.transType)
        }
    }

//    SideEffect {
//        if (vm.transactions.isEmpty()) {
//            navController.navigate(Screen.Home.route)
//        }
//    }

    CategoryDetailsScreen(category = vm.category, transactions = vm.transactions, onDelete = { id ->
        vm.deleteTransaction(id)
    })
}

@Composable
private fun CategoryDetailsScreen(
    category: Category?,
    transactions: List<Transaction>,
    onDelete: (UUID) -> Unit = {},
) {
    val navController = LocalNavController.current

    if (category != null) {
        Column() {
            ScreenHeader(category.label, category.color)
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)

            ) {
                items(transactions.size) { idx ->

                    val transaction: Transaction = transactions[idx]

                    TransactionsListItem(transaction, category, onDelete = { onDelete(transaction.id) }) {
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
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TransactionsDetailsScreenPreview(
) {
    NavProvider {
        AppLayout {
            CategoryDetailsScreen(expenseCategories[0], mockTransactions)
        }
    }
}