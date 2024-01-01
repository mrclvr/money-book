package com.lvrmrc.moneybook.data.source.db

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lvrmrc.moneybook.data.expenseCategoryEntities
import com.lvrmrc.moneybook.data.incomeCategoryEntities
import com.lvrmrc.moneybook.data.mockTransactionEntities
import com.lvrmrc.moneybook.data.source.db.dao.CategoryDao
import com.lvrmrc.moneybook.data.source.db.dao.TransactionDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider


class DatabaseSeeder(
    private val categoryProvider: Provider<CategoryDao>, private val transactionProvider: Provider<TransactionDao>
) : RoomDatabase.Callback() {

    private val appScope = CoroutineScope(SupervisorJob())

    /**
     * Seeds all data on creation
     */
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        seedAll()
    }

    /**
     * Runs all seeders
     */
    private fun seedAll() {
        appScope.launch(Dispatchers.IO) {
            seedExpenseCategories()
            seedIncomeCategories()
            seedTransactions()
        }
    }

    /**
     * Seeds default expense categories
     */
    private suspend fun seedExpenseCategories() {
        categoryProvider.get().insert(*expenseCategoryEntities.toTypedArray())
    }

    /**
     * Seeds default income categories
     */
    private suspend fun seedIncomeCategories() {
        categoryProvider.get().insert(*incomeCategoryEntities.toTypedArray())
    }

    /**
     * Seeds default transactions
     */
    private suspend fun seedTransactions() {
        transactionProvider.get().insert(*mockTransactionEntities.toTypedArray())
    }
}