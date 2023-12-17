package com.lvrmrc.moneybook.presentation.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvrmrc.moneybook.data.AppState
import com.lvrmrc.moneybook.data.repository.CategoryRepositoryImpl
import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class TransactionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val appState: AppState,
    private val transactionRepo: TransactionRepositoryImpl,
    private val categoryRepo: CategoryRepositoryImpl
) : ViewModel() {
    
    private val transactionId: String? = savedStateHandle["transactionId"]

    private var _transaction by mutableStateOf(
        Transaction(type = appState.transType)
    )
    val transaction: Transaction get() = _transaction

    private var _category by mutableStateOf<Category?>(
        null
    )
    val category: Category? get() = _category

    fun setCategory(category: Category?) {
        _category = category
    }

    private var _amount by mutableStateOf(transaction.amount.toString())
    val amount: String get() = _amount

    fun setAmount(amount: String) {
        _amount = amount
    }

    private var _notes by mutableStateOf(transaction.notes)
    val notes: String get() = _notes
    fun setNotes(notes: String) {
        _notes = notes
    }

    private var _date by mutableStateOf(transaction.date)
    val date: LocalDateTime get() = _date
    fun setDate(date: LocalDateTime) {
        _date = date
    }

    private val _hasChanges = derivedStateOf {
        transaction.let {
            amount.toDoubleOrNull() != it.amount || notes != it.notes || date != it.date || it.categoryId != category?.id
        }
    }

    private val _areFieldsValid = derivedStateOf {
        amount.isNotBlank() && notes.isNotBlank() && category !== null
    }

    val fabEnabled = derivedStateOf { _hasChanges.value && _areFieldsValid.value }


    init {
        initTransaction()
    }

    private fun initTransaction() {
//        appState.setLoading(true)
        if (transactionId != null) {
            viewModelScope.launch {
                _transaction = transactionRepo.getById(UUID.fromString(transactionId))

                val (_, notes, amount, date, _, categoryId) = transaction
                _notes = notes
                _amount = amount.toString()
                _date = date
                _category = categoryId.let { categoryRepo.getById(it) }
//            appState.setLoading(false)
            }
        }
    }

    fun addTransaction() {
        viewModelScope.launch {
            category.let { it ->
                when (it) {
                    null -> println("No category was selected, please fill all the requested fields")
                    else -> {
                        _transaction.amount = amount.toDouble()
                        _transaction.notes = notes
                        _transaction.date = date
                        category?.let { _transaction.categoryId = it.id }

                        transactionRepo.insert(transaction)
                    }
                }
            }
        }
    }
}