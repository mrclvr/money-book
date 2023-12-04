package com.lvrmrc.moneybook.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvrmrc.moneybook.data.AppState
import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.model.TransactionWithCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
//    savedStateHandle: SavedStateHandle,
    private val transactionRepo: TransactionRepositoryImpl,
//    private val categoryRepo: CategoryRepositoryImpl,
    private val appState: AppState
) : ViewModel() {

    val animationLaunched = mutableStateOf(false)

    private val transType = mutableStateOf(TransactionType.EXPENSE)

    //    val total by derivedStateOf { _transactions.value.sumOf { it.amount } }
    val total by derivedStateOf { _catTransactions.value.sumOf { it.total } }
    private val _period = mutableStateOf("Day")

    private val _transactions = mutableStateOf<List<TransactionWithCategory>>(emptyList())
    val transactions: State<List<TransactionWithCategory>> = _transactions

    private val _catTransactions = mutableStateOf<List<CategoryWithTransactions>>(emptyList())
    val catTransactions: State<List<CategoryWithTransactions>> = _catTransactions

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

    fun setAnimLaunched() {
        animationLaunched.value = true
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

    private fun groupTransactionsByCategory(transactions: List<TransactionWithCategory>): List<CategoryWithTransactions> {
        val categories: ArrayList<CategoryWithTransactions> =
            ArrayList(transactions.map { it.category.toCategoryWithTransactions() }.distinctBy { it.label })

        transactions.forEach { trans ->
            categories.first { it.label == trans.category.label }.transactions.add(trans.toTransaction())
        }

        categories.forEach {
            it.total = it.transactions.sumOf { t -> t.amount }
        }

        return categories.toList()

    }

    suspend fun getByPeriod(
        period: String, date: LocalDateTime = LocalDateTime.now()
    ) {
        var result: List<TransactionWithCategory> = emptyList()
        _period.value = period

        viewModelScope.launch {
            when (_period.value) {
                "Day" -> {
                    result = transactionRepo.getDayTransactions(transType.value, date.format(DateTimeFormatter.BASIC_ISO_DATE))
                }

                "Month" -> {
                    result = transactionRepo.getMonthTransactions(transType.value, date.monthValue, date.year)
                }

                "Year" -> {
                    result = transactionRepo.getYearTransactions(transType.value, date.year)
                }

                else -> {}
            }
            _catTransactions.value = groupTransactionsByCategory(result)

            println("RESULTS")
            println(catTransactions)
        }

    }

}