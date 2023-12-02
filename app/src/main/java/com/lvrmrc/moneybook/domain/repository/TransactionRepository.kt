package com.lvrmrc.moneybook.domain.repository

import com.lvrmrc.moneybook.data.source.db.dao.TransactionDao
import com.lvrmrc.moneybook.data.source.db.entity.TransactionEntity
import com.lvrmrc.moneybook.data.source.db.entity.TransactionWithCategoryEntity
import com.lvrmrc.moneybook.domain.model.TransactionType
import java.time.LocalDateTime
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 *
 * Collecting from the Flows in [TransactionDao] is main-safe.  Room supports Coroutines and moves the
 * query execution off of the main thread.
 */
@Singleton
interface TransactionRepository {

    suspend fun insert(transaction: TransactionEntity)

    suspend fun getDayTransactions(type: TransactionType, day: LocalDateTime): List<TransactionEntity>

    suspend fun getMonthTransactions(type: TransactionType, month: Int, year: Int): List<TransactionEntity>

    suspend fun getYearTransactions(type: TransactionType, year: Int): List<TransactionWithCategoryEntity>
}
