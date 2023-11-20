package com.lvrmrc.moneybook.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.lvrmrc.moneybook.data.entity.Transaction
import com.lvrmrc.moneybook.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, repository: TransactionRepository
) : ViewModel() {

//    private var queryString: String? = savedStateHandle["plantName"]

    val transactions: List<Transaction> = repository.getAll()
}