package com.lvrmrc.moneybook.presentation.ui.compose.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.model.TransactionWithCategory
import com.lvrmrc.moneybook.presentation.ui.compose.components.TransactionPeriodRadio
import com.lvrmrc.moneybook.presentation.ui.compose.components.TransactionTypeRadio
import com.lvrmrc.moneybook.presentation.ui.compose.components.TransactionsListItem
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.ScreenHeader
import com.lvrmrc.moneybook.presentation.viewmodel.AppViewModel
import com.lvrmrc.moneybook.presentation.viewmodel.TransactionsListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID

@Composable
fun TransactionsListScreen(
    appVm: AppViewModel = hiltViewModel(), vm: TransactionsListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val toastMessage = stringResource(R.string.transaction_deleted)

    LaunchedEffect(appVm.period, appVm.transType) {
        CoroutineScope(Dispatchers.IO).launch {
            vm.loadTransactions(appVm.period, appVm.transType)
        }
    }

    TransactionsListScreen(period = appVm.period, type = appVm.transType, transactionsByDate = vm.transactionsByDate,
        onSetPeriod = { appVm.setPeriod(it) },
        onSetType = { appVm.setTransType(it) },
        onDelete = {
            vm.deleteTransaction(it)
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        })

}

@Composable
private fun TransactionsListScreen(
    period: TransactionPeriod,
    type: TransactionType,
    transactionsByDate: Map<LocalDate, List<TransactionWithCategory>>,
    onSetPeriod: (TransactionPeriod) -> Unit = {},
    onSetType: (TransactionType) -> Unit = {},
    onDelete: (UUID) -> Unit = {},
) {
    Column() {
        ScreenHeader(stringResource(R.string.all_transactions), colorScheme.primary, Screen.TransactionsList.icon)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TransactionPeriodRadio(period, onSelected = { onSetPeriod(it) })
            TransactionTypeRadio(type, onSelected = { onSetType(it) })

            transactionsByDate.entries.forEach() {
                TransactionsListItem(transactions = it.value, showCategoryIcon = true, onDelete = { id -> onDelete(id) })
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TransactionsListScreenPreview(
) {
    NavProvider {
        TransactionsListScreen(TransactionPeriod.DAY,
            TransactionType.EXPENSE,
            mockTransactions.map { it.toTransactionWithCategory(expenseCategories[0]) }.groupBy {
                it.date.toLocalDate()
            })
    }
}