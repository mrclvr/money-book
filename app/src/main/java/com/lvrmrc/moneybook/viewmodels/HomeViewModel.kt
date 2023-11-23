package com.lvrmrc.moneybook.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvrmrc.moneybook.data.entity.Transaction
import com.lvrmrc.moneybook.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, repository: TransactionRepository
) : ViewModel() {

    private var period: String = "month"
    private val _transactions = mutableStateOf<List<Transaction>>(emptyList())
    private val _total = mutableDoubleStateOf(0.0)

    val transactions: State<List<Transaction>> = _transactions
    val total: State<Double> = _total

    private var _onLoading by mutableStateOf(false)
    val onLoading: Boolean
        get() = _onLoading

    init {
        viewModelScope.launch() {
            _transactions.value = repository.getAll()
            _total.doubleValue = repository.getPeriodTotal()
        }
    }
}