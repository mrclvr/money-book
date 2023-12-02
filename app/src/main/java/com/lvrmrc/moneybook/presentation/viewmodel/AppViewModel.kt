package com.lvrmrc.moneybook.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

//    private val _isLoading: MutableStateFlow<AppState> = MutableStateFlow(AppState.Loading)
//    var isLoading = _isLoading.asStateFlow()

    private var _loading by mutableStateOf(false)
    val loading: Boolean
        get() = _loading

    init {
        setLoading(false)
    }

    fun setLoading(value: Boolean) {
        _loading = value
    }


}