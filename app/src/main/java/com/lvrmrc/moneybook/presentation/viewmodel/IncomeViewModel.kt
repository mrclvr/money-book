package com.lvrmrc.moneybook.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.lvrmrc.moneybook.data.AppState
import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IncomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val repository: TransactionRepositoryImpl, val appState: AppState
) : ViewModel() {

    fun setLoading(value: Boolean) = appState::setLoading
    val loading = appState.loading

}