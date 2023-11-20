package com.lvrmrc.moneybook.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.lvrmrc.moneybook.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, repository: TransactionRepository
) : ViewModel() {

    private var period: String = "month"
//    private var period: String? = savedStateHandle["period"]

    val total: Double = repository.getPeriodTotal()
}