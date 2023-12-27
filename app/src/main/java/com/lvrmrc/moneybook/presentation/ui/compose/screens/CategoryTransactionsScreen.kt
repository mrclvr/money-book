package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.lvrmrc.moneybook.presentation.ui.compose.components.TransactionsListItem
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.ScreenHeader
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.navigateDefault
import com.lvrmrc.moneybook.presentation.viewmodel.AppViewModel
import com.lvrmrc.moneybook.presentation.viewmodel.CategoryDetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun CategoryTransactionsScreen(
    appVm: AppViewModel = hiltViewModel(), vm: CategoryDetailsViewModel = hiltViewModel()
) {

    LaunchedEffect(appVm.period, appVm.transType) {
        CoroutineScope(Dispatchers.IO).launch {
            vm.loadCategoryTransactions(appVm.period, appVm.transType)
        }
    }

    CategoryTransactionsScreen(category = vm.category, transactions = vm.transactions, onDelete = { id ->
        vm.deleteTransaction(id)
    })
}

@Composable
private fun CategoryTransactionsScreen(
    category: Category?,
    transactions: List<Transaction>,
    onDelete: (UUID) -> Unit = {},
) {
    val navController = LocalNavController.current

//    if (transactions.isEmpty()) {
//        navController.navigateDefault(Screen.Home.route)
//    }

    if (category != null) {
        Column() {
            ScreenHeader(category.label, category.color)
            LazyColumn(
                modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(15.dp), verticalArrangement = Arrangement.spacedBy(15.dp)

            ) {
                items(transactions) { trans ->
                    TransactionsListItem(trans, category, onDelete = { onDelete(trans.id) }) {
                        navController.navigateDefault("${Screen.Transaction.route}?transactionId=${trans.id}")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CategoryTransactionsScreenPreview(
) {
    NavProvider {
        CategoryTransactionsScreen(expenseCategories[0], mockTransactions)
    }
}