package com.lvrmrc.moneybook.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.lvrmrc.moneybook.domain.model.TransactionType
import javax.inject.Singleton

@Singleton
class AppState {
    private var _loading by mutableStateOf(false)
    private var _period by mutableStateOf("Day")
    private var _transType by mutableStateOf(TransactionType.EXPENSE)
//    private var _currentTransaction by mutableStateOf<TransactionWithCategory?>(null)

    val loading: Boolean
        get() = _loading
    val period: String
        get() = _period
    val transType: TransactionType
        get() = _transType
//    val currentTransaction: TransactionWithCategory?
//        get() = _currentTransaction

    fun setLoading(value: Boolean) {
        _loading = value
    }

    fun setPeriod(value: String) {
        _period = value
    }

    fun setTransType(type: TransactionType) {
        _transType = type
    }

//    fun setCurrentTransaction(transaction: TransactionWithCategory?) {
//        _currentTransaction = transaction
//    }

//    init {
//        setLoading(false)
//    }


    companion object {
        @Volatile
        private var instance: AppState? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: AppState().also { instance = it }
        }
    }
}
