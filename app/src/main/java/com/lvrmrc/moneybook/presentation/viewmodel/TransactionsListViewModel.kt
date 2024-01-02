package com.lvrmrc.moneybook.presentation.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.model.TransactionWithCategory
import com.lvrmrc.moneybook.domain.usecase.GetTransactionsByPeriod
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class TransactionsListViewModel @Inject constructor(
    private val transactionRepo: TransactionRepositoryImpl, val getTransactionsByPeriod: GetTransactionsByPeriod
) : ViewModel() {

    private var _transactions by mutableStateOf<List<TransactionWithCategory>>(emptyList())
    val transactions: List<TransactionWithCategory> get() = _transactions

    val transactionsByDate by derivedStateOf<Map<LocalDate, List<TransactionWithCategory>>> {
        _transactions.groupBy {
            it.date.toLocalDate()
        }
    }

    suspend fun loadTransactions(period: TransactionPeriod, transType: TransactionType, date: LocalDateTime) {
        viewModelScope.launch {
            _transactions = getTransactionsByPeriod(period, transType, date)
        }
    }

    fun deleteTransaction(id: UUID) {
        viewModelScope.launch {
            transactionRepo.deleteById(id)
            _transactions = _transactions.filter { it.id != id }
        }
    }
}