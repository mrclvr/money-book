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
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getTransactionsByPeriod: GetCategoryTransactionsByPeriod

) : ViewModel() {

    private val _catTransactions = mutableStateOf<List<CategoryWithTransactions>>(emptyList())
    val catTransactions: State<List<CategoryWithTransactions>>
        get() = _catTransactions

//    init {
//        loadTransactions()
//    }

    fun loadTransactions(period: TransactionPeriod, transType: TransactionType, date: LocalDateTime) {
        viewModelScope.launch {
            _catTransactions.value = getTransactionsByPeriod(period, transType, date)
        }
    }
}