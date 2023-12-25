package com.lvrmrc.moneybook.data.repository

import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.domain.model.TransactionType
import java.util.UUID
import javax.inject.Singleton

/**
 * Repository module for handling data operations on Transaction entities.
 */
@Singleton
interface TransactionRepository {
    suspend fun deleteById(id: UUID)
    suspend fun insert(transaction: Transaction)
    suspend fun getById(id: UUID): Transaction
    suspend fun getDayTransactions(type: TransactionType, day: String): List<Transaction>
    suspend fun getMonthTransactions(type: TransactionType, month: Int, year: Int): List<Transaction>
    suspend fun getYearTransactions(type: TransactionType, year: Int): List<Transaction>
    suspend fun getDayTransactionsByCategory(categoryId: UUID, type: TransactionType, day: String): List<Transaction>
    suspend fun getMonthTransactionsByCategory(categoryId: UUID, type: TransactionType, month: Int, year: Int): List<Transaction>
    suspend fun getYearTransactionsByCategory(categoryId: UUID, type: TransactionType, year: Int): List<Transaction>
}
