package com.lvrmrc.moneybook.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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

//    private var _onLoading by mutableStateOf(false)
//    val onLoading: Boolean
//        get() = _onLoading

    init {
        loadTransactions()
    }


    fun setTransType(type: TransactionType) {
        println("TAB ${_period.value}")
        transType.value = type
        loadTransactions()
    }

    private fun loadTransactions() {
        appState.execWithLoading {
            viewModelScope.launch {
                getByPeriod(_period.value)
            }
        }
    }

    suspend fun getByPeriod(
        period: String, date: LocalDate = LocalDate.now()
    ) {
        var result: List<Transaction> = emptyList()
        _period.value = period

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
//            println("RESULT")
//            println(_transactions.value)

        }

    }

}