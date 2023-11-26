package com.lvrmrc.moneybook.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AppState private constructor() {

    private var _loading by mutableStateOf(false)
    val loading: Boolean
        get() = _loading

    init {
        setLoading(false)
    }

    fun setLoading(value: Boolean) {
        _loading = value
        println("LOADING $value")
    }

    companion object {
        @Volatile
        private var instance: AppState? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: AppState().also { instance = it }
        }
    }
}
