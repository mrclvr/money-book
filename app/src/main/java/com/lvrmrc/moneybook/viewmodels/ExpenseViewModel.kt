package com.lvrmrc.moneybook.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvrmrc.moneybook.data.AppState
import com.lvrmrc.moneybook.data.entity.Transaction
import com.lvrmrc.moneybook.data.entity.TransactionType
import com.lvrmrc.moneybook.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val repository: TransactionRepository, private val appState: AppState
) : ViewModel() {

    private val transType = mutableStateOf(TransactionType.EXPENSE)
    val total by derivedStateOf { _transactions.value.sumOf { it.amount } }
    private val _period = mutableStateOf("Day")

    private val _transactions = mutableStateOf<List<Transaction>>(emptyList())
    val transactions: State<List<Transaction>> = _transactions

    val periodTabIndex = derivedStateOf {
        when (_period.value) {
            "Day" -> 0
            "Month" -> 1
            "Year" -> 2
            else -> {
                0
            }
        }
    }

//    private val _pippo = mutableStateListOf<List<Transaction>>(emptyList())
//    val pippo: SnapshotStateList<List<Transaction>> = _pippo

    private var _onLoading by mutableStateOf(false)
    val onLoading: Boolean
        get() = _onLoading

//    init {
//        loadTransactions()
//    }


    fun setTransType(type: TransactionType) {
        transType.value = type
        loadTransactions()
    }

    private fun loadTransactions() {
        viewModelScope.launch {
            appState.setLoading(true)
            getByPeriod(_period.value)
            appState.setLoading(false)

//            _pippo.clear()
//            _pippo.addAll(listOf(repository.getAll()))
        }
    }

    suspend fun addExpense(transaction: Transaction) {
        viewModelScope.launch {
            _onLoading = true
            repository.insert(transaction)
            loadTransactions()
            _onLoading = false
        }
    }

    suspend fun getByPeriod(
        period: String, date: LocalDate = LocalDate.now()
    ) {
        _period.value = period
        var result: List<Transaction> = emptyList()

        viewModelScope.launch {
            when (_period.value) {
                "Day" -> {
                    result = repository.getDayTransactions(transType.value, date)
                }

                "Month" -> {
                    result = repository.getMonthTransactions(transType.value, date.monthValue, date.year)
                }

                "Year" -> {
                    result = repository.getYearTransactions(transType.value, date.year)
                }

                else -> {}
            }
            _transactions.value = result.reversed()
            println("TOTAL $total")

        }
    }
//
//    fun deleteTask(task: Task) {
//        viewModelScope.launch {
//            repository.deleteTask(task)
//            _tasks.value = repository.getAllTask()
//        }
//    }

}