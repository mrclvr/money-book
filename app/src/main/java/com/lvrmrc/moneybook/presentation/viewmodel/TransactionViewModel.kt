package com.lvrmrc.moneybook.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvrmrc.moneybook.data.AppState
import com.lvrmrc.moneybook.data.mockCategories
import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.TransactionWithCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class TransactionViewModel @Inject constructor(
    val appState: AppState, private val transactionRepo: TransactionRepositoryImpl
) : ViewModel() {

    private val _amount = mutableStateOf("")
    val amount: MutableState<String> = _amount

    fun setAmount(value: String) {
        _amount.value = value
    }

    private val _notes = mutableStateOf("")
    val notes: MutableState<String> = _notes

    fun setNotes(value: String) {
        _notes.value = value
    }

    private val _category: MutableState<Category?> = mutableStateOf(null)
    val category: MutableState<Category?> = _category

    fun setCategory(value: Category) {
        _category.value = value
    }

    private val _date = mutableStateOf(LocalDateTime.now())
    val date: MutableState<LocalDateTime> = _date

    fun setDate(timestamp: Long) {
//        val basicIsoFormat = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        val instant = Instant.ofEpochMilli(timestamp)
        val zone = ZoneId.systemDefault()

        _date.value = LocalDateTime.ofInstant(instant, zone)
    }

    fun addTransaction() {
        viewModelScope.launch {
            transactionRepo.insert(
                TransactionWithCategory(
                    id = UUID.randomUUID(),
                    amount = amount.value.toDouble(),
                    notes = notes.value,
                    type = appState.transType,
                    date = date.value,
                    category = category.value ?: mockCategories[0]
//                    date = LocalDateTime.parse(date.value, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                )
            )
        }
    }
}