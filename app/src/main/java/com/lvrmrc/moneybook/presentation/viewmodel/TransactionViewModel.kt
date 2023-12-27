package com.lvrmrc.moneybook.presentation.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvrmrc.moneybook.data.repository.CategoryRepositoryImpl
import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.utils.removeDecimal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class TransactionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val transactionRepo: TransactionRepositoryImpl,
    private val categoryRepo: CategoryRepositoryImpl
) : ViewModel() {

    val transactionId: String? = savedStateHandle["transactionId"]

    private var _transaction by mutableStateOf<Transaction?>(null)
    private var _category by mutableStateOf<Category?>(null)
    private var _amount by mutableStateOf(transaction?.let { if (it.amount != 0.0) "${it.amount}" else null } ?: "")
    private var _notes by mutableStateOf(transaction?.notes ?: "")
    private var _date by mutableStateOf(transaction?.date ?: LocalDateTime.now())

    val transaction: Transaction? get() = _transaction
    val category: Category? get() = _category
    val amount: String get() = _amount
    val notes: String get() = _notes
    val date: LocalDateTime get() = _date

    private val _hasChanges = derivedStateOf {
        transaction?.let {
            amount.toDoubleOrNull() != it.amount || notes != it.notes || date != it.date || it.categoryId != category?.id
        } ?: true
    }

    private val _areFieldsValid = derivedStateOf {
        amount.isNotBlank()
//                && notes.isNotBlank()
                && category !== null
    }

    val fabEnabled = derivedStateOf { _hasChanges.value && _areFieldsValid.value }

    fun setCategory(category: Category?) {
        _category = category
    }

    fun setAmount(amount: String) {
        _amount = amount
    }

    fun setNotes(notes: String) {
        _notes = notes
    }

    fun setDate(date: LocalDateTime) {
        _date = date
    }

    init {
        initTransaction()
    }

    private fun initTransaction() {
        transactionId?.let { id ->
            viewModelScope.launch {
                _transaction = transactionRepo.getById(UUID.fromString(id))

                _transaction?.let { tr ->
                    val (_, notes, amount, date, _, categoryId) = tr
                    _notes = notes
                    _amount = amount.removeDecimal().toString()
                    _date = date
                    loadCategory(categoryId)
                }
            }
        }
    }

    fun loadCategory(categoryId: UUID) {
        viewModelScope.launch {
            setCategory(categoryId.let { categoryRepo.getById(it) })
        }
    }

    fun addTransaction(transType: TransactionType) {
        viewModelScope.launch {
            category.let { cat ->
                when (cat) {
                    null -> println("No category was selected, please fill all the requested fields")
                    else -> {
                        transactionRepo.insert(
                            Transaction(
                                id = _transaction?.id ?: UUID.randomUUID(),
                                amount = amount.toDouble(),
                                notes = notes,
                                date = date,
                                type = _transaction?.type ?: transType,
                                categoryId = cat.id
                            )
                        )
                    }
                }
            }
        }
    }
}