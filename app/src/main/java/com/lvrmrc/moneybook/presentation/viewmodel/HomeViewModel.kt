package com.lvrmrc.moneybook.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.usecase.GetCategoryTransactionsByPeriod
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getTransactionsByPeriod: GetCategoryTransactionsByPeriod

) : ViewModel() {

    private val _catTransactions = mutableStateOf<List<CategoryWithTransactions>>(emptyList())
    val catTransactions: State<List<CategoryWithTransactions>>
        get() = _catTransactions

    private val _selectedCategory = mutableStateOf<CategoryWithTransactions?>(null)
    val selectedCategory: State<CategoryWithTransactions?>
        get() = _selectedCategory

//    init {
//        loadTransactions()
//    }

    fun setCategory(category: CategoryWithTransactions) {
        _selectedCategory.value = category
    }


    fun loadTransactions(period: TransactionPeriod, transType: TransactionType) {
        viewModelScope.launch {
            _catTransactions.value = getTransactionsByPeriod(period, transType)
        }
    }
}