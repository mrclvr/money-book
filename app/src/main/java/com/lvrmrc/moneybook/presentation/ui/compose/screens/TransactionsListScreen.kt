package com.lvrmrc.moneybook.presentation.ui.compose.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.lvrmrc.moneybook.presentation.ui.compose.components.DateDirection
import com.lvrmrc.moneybook.presentation.ui.compose.components.DateSelector
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
import java.time.LocalDateTime
import java.util.UUID

@Composable
fun TransactionsListScreen(
    appVm: AppViewModel = hiltViewModel(), vm: TransactionsListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val toastMessage = stringResource(R.string.transaction_deleted)

    LaunchedEffect(appVm.period, appVm.transType, appVm.date) {
        CoroutineScope(Dispatchers.IO).launch {
            vm.loadTransactions(appVm.period, appVm.transType, appVm.date)
        }
    }

    TransactionsListScreen(period = appVm.period,
        type = appVm.transType,
        currentDate = appVm.date,
        transactionsByDate = vm.transactionsByDate,
        onSetPeriod = {
            appVm.setDate(LocalDateTime.now())
            appVm.setPeriod(it)
        },
        onSetType = { appVm.setTransType(it) },
        onDateBack = { appVm.shiftDate(DateDirection.MINUS) },
        onDateForward = { appVm.shiftDate(DateDirection.PLUS) },
        onDelete = {
            vm.deleteTransaction(it)
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        })

}

@Composable
private fun TransactionsListScreen(
    period: TransactionPeriod,
    type: TransactionType,
    currentDate: LocalDateTime,
    transactionsByDate: Map<LocalDate, List<TransactionWithCategory>>,
    onSetPeriod: (TransactionPeriod) -> Unit = {},
    onSetType: (TransactionType) -> Unit = {},
    onDateBack: () -> Unit = {},
    onDateForward: () -> Unit = {},
    onDelete: (UUID) -> Unit = {},
) {
    println(currentDate)
    Column() {
        ScreenHeader(title = stringResource(R.string.all_transactions), icon = Screen.TransactionsList.icon)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                DateSelector(period, currentDate, onDateBack = onDateBack, onDateForward = onDateForward)
            }
            item {
                TransactionPeriodRadio(period, onSelected = { onSetPeriod(it) })
            }
            item {
                TransactionTypeRadio(type, onSelected = { onSetType(it) })
            }
            item {
                Spacer(Modifier.height(0.dp))
            }
            transactionsByDate.entries.forEach() {
                item {
                    TransactionsListItem(transactions = it.value, showCategoryIcon = true, onDelete = { id -> onDelete(id) })
                }
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
            LocalDateTime.now(),
            mockTransactions.map { it.toTransactionWithCategory(expenseCategories[0]) }.groupBy {
                it.date.toLocalDate()
            })
    }
}