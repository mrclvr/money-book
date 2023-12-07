package com.lvrmrc.moneybook.domain.repository

import com.lvrmrc.moneybook.data.source.db.dao.TransactionDao
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.model.TransactionWithCategory
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 *
 * Collecting from the Flows in [TransactionDao] is main-safe.  Room supports Coroutines and moves the
 * query execution off of the main thread.
 */
@Singleton
interface TransactionRepository {

    suspend fun insert(transaction: TransactionWithCategory): Unit

    suspend fun getDayTransactions(type: TransactionType, day: String): List<TransactionWithCategory>

    suspend fun getMonthTransactions(type: TransactionType, month: Int, year: Int): List<TransactionWithCategory>

    suspend fun getYearTransactions(type: TransactionType, year: Int): List<TransactionWithCategory>
}
