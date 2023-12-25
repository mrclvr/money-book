package com.lvrmrc.moneybook.deprecated

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType
import javax.inject.Singleton

@Singleton
class AppState {
    private var _loading by mutableStateOf(false)
    private var _pieAnimationPlayed by mutableStateOf(false)
    private var _transType by mutableStateOf(TransactionType.EXPENSE)
    private var _period by mutableStateOf(TransactionPeriod.DAY)

    val loading: Boolean
        get() = _loading
    val pieAnimationPlayed: Boolean
        get() = _pieAnimationPlayed
    val period: TransactionPeriod
        get() = _period
    val transType: TransactionType
        get() = _transType

    fun setLoading(value: Boolean) {
        _loading = value
    }

    fun setPieAnimationPlayed(value: Boolean) {
        _pieAnimationPlayed = value
    }

    fun setTransType(type: TransactionType) {
        _transType = type
    }

    fun setPeriod(period: TransactionPeriod) {
        _period = period
    }

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
