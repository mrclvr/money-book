package com.lvrmrc.moneybook.data.source.db

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lvrmrc.moneybook.data.source.db.dao.CategoryDao
import com.lvrmrc.moneybook.data.source.db.dao.TransactionDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider


class DatabaseCallback(
    private val categoryProvider: Provider<CategoryDao>, private val transactionProvider: Provider<TransactionDao>
) : RoomDatabase.Callback() {

    private val appScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        seedAll()
    }

    private fun seedAll() {
        appScope.launch(Dispatchers.IO) {
            seedCategories()
            seedTransactions()
        }
    }

    private suspend fun seedCategories() {
        categoryProvider.get().insert(*mockCategoryEntities.toTypedArray())
    }

    private suspend fun seedTransactions() {
        transactionProvider.get().insert(*mockTransactionEntities.toTypedArray())
    }
}