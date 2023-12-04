package com.lvrmrc.moneybook.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvrmrc.moneybook.data.AppState
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.usecase.GetTransactionsByPeriod
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
//    savedStateHandle: SavedStateHandle,
//    private val categoryRepo: CategoryRepositoryImpl,
//    private val transactionRepo: TransactionRepositoryImpl,
    val appState: AppState, val getTransactionsByPeriod: GetTransactionsByPeriod

) : ViewModel() {

    val animationLaunched = mutableStateOf(false)

    val total by derivedStateOf { _catTransactions.value.sumOf { it.total } }

//    private val _transactions = mutableStateOf<List<TransactionWithCategory>>(emptyList())
//    val transactions: State<List<TransactionWithCategory>> = _transactions

    private val _catTransactions = mutableStateOf<List<CategoryWithTransactions>>(emptyList())
    val catTransactions: State<List<CategoryWithTransactions>> = _catTransactions

    val periodTabIndex = derivedStateOf {
        when (appState.period) {
            "Day" -> 0
            "Month" -> 1
            "Year" -> 2
            else -> {
                0
            }
        }
    }

//    init {
//        loadTransactions()
//    }

    fun setAnimLaunched() {
        animationLaunched.value = true
    }

    fun loadTransactions(period: String = appState.period) {
        appState.setLoading(true)

        viewModelScope.launch {
            appState.setPeriod(period)
            _catTransactions.value = getTransactionsByPeriod()
            appState.setLoading(false)
        }
    }
}