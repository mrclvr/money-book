package com.lvrmrc.moneybook.presentation.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvrmrc.moneybook.data.repository.CategoryRepositoryImpl
import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.presentation.ui.compose.components.DateDirection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val categoryRepo: CategoryRepositoryImpl, private val transactionRepo: TransactionRepositoryImpl
) : ViewModel() {

    private var _loading by mutableStateOf(false)
    private var _pieAnimationPlayed by mutableStateOf(false)
    private var _categories by mutableStateOf(emptyList<Category>())
    private var _transType by mutableStateOf(TransactionType.EXPENSE)
    private var _period by mutableStateOf(TransactionPeriod.DAY)
    private var _date by mutableStateOf(LocalDateTime.now())

    val expenseCategories by derivedStateOf { _categories.filter { it.type == TransactionType.EXPENSE } }
    val incomeCategories by derivedStateOf { _categories.filter { it.type == TransactionType.INCOME } }

    val loading: Boolean
        get() = _loading
    val pieAnimationPlayed: Boolean
        get() = _pieAnimationPlayed
    val categories: List<Category>
        get() = _categories
    val transType: TransactionType
        get() = _transType
    val period: TransactionPeriod
        get() = _period
    val date: LocalDateTime
        get() = _date

    fun setLoading(value: Boolean) {
        _loading = value
    }

    fun setPieAnimationPlayed(value: Boolean) {
        _pieAnimationPlayed = value
    }

    fun setTransType(type: TransactionType) {
        _transType = type
    }

    fun setPeriod(period: TransactionPeriod) {
        _period = period
    }

    fun setDate(date: LocalDateTime) {
        _date = date
    }

    fun shiftDate(direction: DateDirection) {
        val plus = direction == DateDirection.PLUS

        _date = when (period) {
            TransactionPeriod.DAY -> if (plus) _date.plusDays(1) else _date.minusDays(1)
            TransactionPeriod.MONTH -> if (plus) _date.plusMonths(1) else _date.minusMonths(1)
            TransactionPeriod.YEAR -> if (plus) _date.plusYears(1) else _date.minusYears(1)
            else -> _date
        }
    }

    fun setCategories() {
        viewModelScope.launch {
            _categories = categoryRepo.getAll()
        }
    }

    init {
        setCategories()
    }
}