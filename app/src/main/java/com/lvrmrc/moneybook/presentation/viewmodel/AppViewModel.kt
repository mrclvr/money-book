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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    val expenseCategories by derivedStateOf { _categories.filter { it.type == TransactionType.EXPENSE } }
    val incomeCategories by derivedStateOf { _categories.filter { it.type == TransactionType.INCOME } }

    val loading: Boolean
        get() = _loading
    val pieAnimationPlayed: Boolean
        get() = _pieAnimationPlayed
    val categories: List<Category>
        get() = _categories
    val period: TransactionPeriod
        get() = _period
    val transType: TransactionType
        get() = _transType

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

    fun setCategories() {
        viewModelScope.launch {
            _categories = categoryRepo.getAll()
        }
    }

    init {
        setCategories()
    }
}