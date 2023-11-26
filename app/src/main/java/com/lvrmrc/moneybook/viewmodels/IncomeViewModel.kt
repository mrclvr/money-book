package com.lvrmrc.moneybook.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.lvrmrc.moneybook.data.AppState
import com.lvrmrc.moneybook.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IncomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val repository: TransactionRepository, val appState: AppState
) : ViewModel() {

    fun setLoading(value: Boolean) = appState::setLoading
    val loading = appState.loading

}