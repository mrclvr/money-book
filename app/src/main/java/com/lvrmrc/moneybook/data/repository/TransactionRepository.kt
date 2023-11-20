package com.lvrmrc.moneybook.data.repository

import com.lvrmrc.moneybook.data.dao.TransactionDao
import com.lvrmrc.moneybook.data.entity.Transaction
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

    fun getAll(): List<Transaction> {
        return listOf(
            Transaction(id = 123, amount = 5.55, notes = "Food", timestamp = 0),
            Transaction(id = 345, amount = 5.55, notes = "Health", timestamp = 1),
            Transaction(id = 999, amount = 5.55, notes = "Cinema", timestamp = 2)
        )
    }

    fun getPeriodTotal(): Double = 0.01
//    fun getPeriodTotal() = transactionDao.getPeriodTotal()

//    fun getTransaction(transactionId: String) = transactionDao.getTransaction(transactionId)


    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: TransactionRepository? = null

        fun getInstance(transactionDao: TransactionDao) = instance ?: synchronized(this) {
            instance ?: TransactionRepository(transactionDao).also { instance = it }
        }
    }
}
