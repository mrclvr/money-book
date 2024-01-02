package com.lvrmrc.moneybook.presentation.ui.compose.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.data.expenseCategories
import com.lvrmrc.moneybook.data.mockTransactions
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.presentation.ui.compose.components.TransactionsListItem
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.ScreenHeader
import com.lvrmrc.moneybook.presentation.viewmodel.AppViewModel
import com.lvrmrc.moneybook.presentation.viewmodel.CategoryTransactionsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID

@Composable
fun CategoryTransactionsScreen(
    appVm: AppViewModel = hiltViewModel(), vm: CategoryTransactionsViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val toastMessage = stringResource(R.string.transaction_deleted)

    LaunchedEffect(appVm.period, appVm.transType) {
        CoroutineScope(Dispatchers.IO).launch {
            vm.loadCategoryTransactions(appVm.period, appVm.transType, appVm.date)
        }
    }

    CategoryTransactionsScreen(category = vm.category, transactionsByDate = vm.transactionsByDate, onDelete = {
        vm.deleteTransaction(it)
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
    })
}

@Composable
private fun CategoryTransactionsScreen(
    category: Category?,
    transactionsByDate: Map<LocalDate, List<Transaction>>,
    onDelete: (UUID) -> Unit = {},
) {
//    if (transactions.isEmpty()) {
//        navController.navigateDefault(Screen.Home.route)
//    }

    if (category != null) {
        Column() {
            ScreenHeader(title = category.label, color = category.color, icon = category.icon, textColor = colorScheme.background)
            LazyColumn(
                modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                transactionsByDate.entries.forEach() { group ->
                    item {
                        TransactionsListItem(transactions = group.value.map { it.toTransactionWithCategory(category) }, category,
//                        outlined = true,
                            onDelete = { id -> onDelete(id) })
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
        CategoryTransactionsScreen(expenseCategories[0], mockTransactions.groupBy {
            it.date.toLocalDate()
        })
    }
}