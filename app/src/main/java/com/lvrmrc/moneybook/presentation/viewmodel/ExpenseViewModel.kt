package com.lvrmrc.moneybook.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvrmrc.moneybook.data.AppState
import com.lvrmrc.moneybook.data.repository.CategoryRepositoryImpl
import com.lvrmrc.moneybook.data.source.db.entity.TransactionEntity
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
//    savedStateHandle: SavedStateHandle,
//    private val transactionRepo: TransactionRepositoryImpl,
    private val categoryRepo: CategoryRepositoryImpl, private val appState: AppState
) : ViewModel() {

    val animationLaunched = mutableStateOf(false)

    private val transType = mutableStateOf(TransactionType.EXPENSE)

    //    val total by derivedStateOf { _transactions.value.sumOf { it.amount } }
    val total by derivedStateOf { _catTransactions.value.sumOf { it.total } }
    private val _period = mutableStateOf("Day")

    private val _transactions = mutableStateOf<List<TransactionEntity>>(emptyList())
    val transactions: State<List<TransactionEntity>> = _transactions

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

    suspend fun getByPeriod(
        period: String, date: LocalDateTime = LocalDateTime.now()
    ) {
        var result: List<CategoryWithTransactions> = emptyList()
        _period.value = period

        viewModelScope.launch {
            when (_period.value) {
                "Day" -> {
                    result = categoryRepo.getDayCategoriesWithTransactions(transType.value, date)
                }

                "Month" -> {
                    result = categoryRepo.getMonthCategoriesWithTransactions(transType.value, date.monthValue, date.year)
                }

                "Year" -> {
                    result = categoryRepo.getYearCategoriesWithTransactions(transType.value, date.year)
                }

                else -> {}
            }
            _catTransactions.value = result

            println("RESULTS")
            println(result)
        }

    }

}