package com.lvrmrc.moneybook.data.repository

import com.lvrmrc.moneybook.data.dao.TransactionDao
import com.lvrmrc.moneybook.data.entity.Transaction
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 *
 * Collecting from the Flows in [TransactionDao] is main-safe.  Room supports Coroutines and moves the
 * query execution off of the main thread.
 */
@Singleton
class TransactionRepository @Inject constructor(private val transactionDao: TransactionDao) {

    suspend fun insert(transaction: Transaction) = transactionDao.insert(transaction)
    suspend fun getAll(): List<Transaction> = transactionDao.getAll()

    suspend fun getDayTransactions(day: LocalDate): List<Transaction> = transactionDao.getDayTransactions(day)

    suspend fun getMonthTransactions(month: Int, year: Int): List<Transaction> = transactionDao.getMonthTransactions(month, year)
    suspend fun getYearTransactions(year: Int): List<Transaction> = transactionDao.getYearTransactions(year)

    suspend fun getPeriodTotal(): Double = transactionDao.getPeriodTotal()


    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: TransactionRepository? = null

        fun getInstance(transactionDao: TransactionDao) = instance ?: synchronized(this) {
            instance ?: TransactionRepository(transactionDao).also { instance = it }
        }
    }
}
