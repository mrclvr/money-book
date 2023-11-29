package com.lvrmrc.moneybook.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AppState private constructor() {

    private var _loading by mutableStateOf(false)
    val loading: Boolean
        get() = _loading

    fun setLoading(value: Boolean) {
        _loading = value
    }

//    init {
//        setLoading(false)
//    }

    fun execWithLoading(function: () -> Unit) {
        _loading = true
//        println("EXEC WITH LOADING $_loading")
        function()
        _loading = false
//        println("EXEC WITH LOADING $_loading")
    }

    companion object {
        @Volatile
        private var instance: AppState? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: AppState().also { instance = it }
        }
    }
}
