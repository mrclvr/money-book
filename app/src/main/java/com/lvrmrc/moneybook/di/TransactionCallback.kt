package com.lvrmrc.moneybook.di

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lvrmrc.moneybook.data.dao.TransactionDao
import com.lvrmrc.moneybook.data.defaultTransactions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider


class TransactionCallback(
    private val transactionProvider: Provider<TransactionDao>
) : RoomDatabase.Callback() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        seedTransactions()
    }

    private fun seedTransactions() {
        applicationScope.launch(Dispatchers.IO) {
            transactionProvider.get().insert(*defaultTransactions)
        }
    }
}