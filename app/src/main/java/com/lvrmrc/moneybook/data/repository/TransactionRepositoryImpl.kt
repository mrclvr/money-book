package com.lvrmrc.moneybook.data.repository

import com.lvrmrc.moneybook.data.source.db.dao.TransactionDao
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.model.TransactionWithCategory
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * TransactionRepository implementation
 */
@Singleton
class TransactionRepositoryImpl @Inject constructor(private val transactionDao: TransactionDao) : TransactionRepository {

    /**
     * Deletes transaction by its id
     *
     * @param id  the transaction's UUID
     */
    override suspend fun deleteById(id: UUID) = transactionDao.deleteById(id)

    /**
     * Inserts new transaction
     *
     * @param transaction the transaction to insert
     */
    override suspend fun insert(transaction: Transaction) = transactionDao.insert(transaction.toEntity())

    /**
     * Gets a transaction by its UUID
     *
     *  @param id the transaction's UUID
     */
    override suspend fun getById(id: UUID): Transaction = transactionDao.getById(id).toDomain()

    /**
     * Gets a single day's transactions
     *
     * @param type the transaction's type
     * @param day the day in string format
     */
    override suspend fun getDayTransactions(type: TransactionType, day: String): List<Transaction> =
        transactionDao.getDayTransactions(type, day).map { it.toDomain() }

    override suspend fun getDayTransactionsWithCategory(type: TransactionType, day: String): List<TransactionWithCategory> =
        transactionDao.getDayTransactionsWithCategory(type, day).map { it.toDomain() }

    /**
     * Gets a month's transactions
     *
     * @param type the transaction's type
     * @param month the month as int
     * @param year the year as int
     */
    override suspend fun getMonthTransactions(type: TransactionType, month: Int, year: Int): List<Transaction> =
        transactionDao.getMonthTransactions(type, month, year).map { it.toDomain() }

    override suspend fun getMonthTransactionsWithCategory(type: TransactionType, month: Int, year: Int): List<TransactionWithCategory> =
        transactionDao.getMonthTransactionsWithCategory(type, month, year).map { it.toDomain() }

    /**
     * Gets a year's transactions
     *
     * @param type the transaction's type
     * @param year the year as int
     */
    override suspend fun getYearTransactions(type: TransactionType, year: Int): List<Transaction> =
        transactionDao.getYearTransactions(type, year).map { it.toDomain() }

    override suspend fun getYearTransactionsWithCategory(type: TransactionType, year: Int): List<TransactionWithCategory> =
        transactionDao.getYearTransactionsWithCategory(type, year).map { it.toDomain() }

    /**
     * Gets a given category's single day's transactions
     *
     * @param categoryId the category's UUID
     * @param type the transaction's type
     * @param day the day in string format
     */
    override suspend fun getDayTransactionsByCategory(categoryId: UUID, type: TransactionType, day: String): List<Transaction> =
        transactionDao.getDayTransactionsByCategory(categoryId, type, day).map { it.toDomain() }

    /**
     * Gets a given category's month's transactions
     *
     * @param categoryId the category's UUID
     * @param type the transaction's type
     * @param month the month as int
     * @param year the year as int
     */
    override suspend fun getMonthTransactionsByCategory(categoryId: UUID, type: TransactionType, month: Int, year: Int): List<Transaction> =
        transactionDao.getMonthTransactionsByCategory(categoryId, type, month, year).map { it.toDomain() }

    /**
     * Gets a given category's year's transactions
     *
     * @param categoryId the category's UUID
     * @param type the transaction's type
     * @param year the year as int
     */
    override suspend fun getYearTransactionsByCategory(categoryId: UUID, type: TransactionType, year: Int): List<Transaction> =
        transactionDao.getYearTransactionsByCategory(categoryId, type, year).map { it.toDomain() }

//    companion object {
//        @Volatile
//        private var instance: TransactionRepositoryImpl? = null
//
//        fun getInstance(transactionDao: TransactionDao) = instance ?: synchronized(this) {
//            instance ?: TransactionRepositoryImpl(transactionDao).also { instance = it }
//        }
//    }
}
