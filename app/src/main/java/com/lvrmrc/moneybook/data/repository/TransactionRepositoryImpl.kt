package com.lvrmrc.moneybook.data.repository

import com.lvrmrc.moneybook.data.source.db.dao.TransactionDao
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.model.TransactionWithCategory
import com.lvrmrc.moneybook.domain.repository.TransactionRepository
import java.util.UUID
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

    override suspend fun deleteById(id: UUID) = transactionDao.deleteById(id)

    override suspend fun insert(transaction: Transaction) = transactionDao.insert(transaction.toEntity())

    override suspend fun getById(id: UUID): Transaction = transactionDao.getById(id).toDomain()

    override suspend fun getDayTransactions(type: TransactionType, day: String): List<TransactionWithCategory> =
        transactionDao.getDayTransactions(type, day).map { it.toDomain() }

    override suspend fun getMonthTransactions(type: TransactionType, month: Int, year: Int): List<TransactionWithCategory> =
        transactionDao.getMonthTransactions(type, month, year).map { it.toDomain() }

    override suspend fun getYearTransactions(type: TransactionType, year: Int): List<TransactionWithCategory> =
        transactionDao.getYearTransactions(type, year).map { it.toDomain() }

    override suspend fun getDayTransactionsByCategory(categoryId: UUID, type: TransactionType, day: String): List<Transaction> =
        transactionDao.getDayTransactionsByCategory(categoryId, type, day).map { it.toDomain() }

    override suspend fun getMonthTransactionsByCategory(categoryId: UUID, type: TransactionType, month: Int, year: Int): List<Transaction> =
        transactionDao.getMonthTransactionsByCategory(categoryId, type, month, year).map { it.toDomain() }

    override suspend fun getYearTransactionsByCategory(categoryId: UUID, type: TransactionType, year: Int): List<Transaction> =
        transactionDao.getYearTransactionsByCategory(categoryId, type, year).map { it.toDomain() }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: TransactionRepositoryImpl? = null

        fun getInstance(transactionDao: TransactionDao) = instance ?: synchronized(this) {
            instance ?: TransactionRepositoryImpl(transactionDao).also { instance = it }
        }
    }
}
