package com.lvrmrc.moneybook.presentation.viewmodel

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
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.usecase.GetTransactionsByPeriodAndCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val appState: AppState,
    private val categoryRepo: CategoryRepositoryImpl,
    private val transactionRepo: TransactionRepositoryImpl,
    val getTransactionsByPeriodAndCategory: GetTransactionsByPeriodAndCategory
) : ViewModel() {

    private val categoryId: String = checkNotNull(savedStateHandle["categoryId"])

    private var _category by mutableStateOf<Category?>(null)
    val category: Category? get() = _category

    private var _transactions by mutableStateOf<List<Transaction>>(emptyList())
    val transactions: List<Transaction> get() = _transactions

    var updated: Int = 0
//    init {
//        initCategory()
//    }

    suspend fun loadCategoryTransactions(period: TransactionPeriod, transType: TransactionType) {
        viewModelScope.launch {
            val id = UUID.fromString(categoryId)
            _category = categoryRepo.getById(id)
            _transactions = getTransactionsByPeriodAndCategory(id, period, transType)
        }

    }

    fun deleteTransaction(id: UUID) {
        viewModelScope.launch {
            val deleted = transactionRepo.deleteById(id)
            println("DELETED " + deleted.toString())
            _transactions = _transactions.filter { it.id != id }
//            loadCategoryTransactions()
        }

    }
}