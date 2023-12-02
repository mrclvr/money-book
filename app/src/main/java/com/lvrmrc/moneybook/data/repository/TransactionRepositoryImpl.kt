package com.lvrmrc.moneybook.data.repository

import com.lvrmrc.moneybook.data.source.db.dao.TransactionDao
import com.lvrmrc.moneybook.data.source.db.entity.TransactionEntity
import com.lvrmrc.moneybook.data.source.db.entity.TransactionWithCategoryEntity
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.repository.TransactionRepository
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 *
 * Collecting from the Flows in [TransactionDao] is main-safe.  Room supports Coroutines and moves the
 * query execution off of the main thread.
 */
@Singleton
class TransactionRepositoryImpl @Inject constructor(private val transactionDao: TransactionDao) : TransactionRepository {

    override suspend fun insert(transaction: TransactionEntity) = transactionDao.insert(transaction)

    override suspend fun getDayTransactions(type: TransactionType, day: LocalDateTime): List<TransactionEntity> =
        transactionDao.getDayTransactions(type, day)

    override suspend fun getMonthTransactions(type: TransactionType, month: Int, year: Int): List<TransactionEntity> =
        transactionDao.getMonthTransactions(type, month, year)

    override suspend fun getYearTransactions(type: TransactionType, year: Int): List<TransactionWithCategoryEntity> =
        transactionDao.getYearTransactions(type, year)

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: TransactionRepositoryImpl? = null

        fun getInstance(transactionDao: TransactionDao) = instance ?: synchronized(this) {
            instance ?: TransactionRepositoryImpl(transactionDao).also { instance = it }
        }
    }
}
