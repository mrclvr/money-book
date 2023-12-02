package com.lvrmrc.moneybook.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvrmrc.moneybook.data.AppState
import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.data.source.db.defaultCategories
import com.lvrmrc.moneybook.data.source.db.entity.TransactionEntity
import com.lvrmrc.moneybook.domain.model.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class TransactionViewModel @Inject constructor(
    val appState: AppState, private val repository: TransactionRepositoryImpl
) : ViewModel() {

    private val _amount = mutableDoubleStateOf(0.0)
    val amount: MutableState<Double> = _amount

    fun setAmount(value: Double) {
        _amount.doubleValue = value
    }

    private val _notes = mutableStateOf("")
    val notes: MutableState<String> = _notes

    fun setNotes(value: String) {
        _notes.value = value
    }

    private val _date = mutableStateOf("")
    val date: MutableState<String> = _date

    fun setDate(value: String) {
        _date.value = value
    }

    fun addTransaction() {
        viewModelScope.launch {
            repository.insert(
                TransactionEntity(
                    amount = amount.value,
                    notes = "TEST",
                    type = TransactionType.EXPENSE,
                    date = LocalDateTime.parse(date.value, DateTimeFormatter.BASIC_ISO_DATE),
                    categoryId = defaultCategories[0].id
                )
            )
        }
    }
}