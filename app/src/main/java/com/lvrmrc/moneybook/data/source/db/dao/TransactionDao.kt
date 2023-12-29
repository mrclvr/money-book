package com.lvrmrc.moneybook.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.lvrmrc.moneybook.data.source.db.entity.TransactionEntity
import com.lvrmrc.moneybook.data.source.db.entity.TransactionWithCategoryEntity
import com.lvrmrc.moneybook.domain.model.TransactionType
import java.util.UUID


@Dao
interface TransactionDao : BaseDao<TransactionEntity> {

    @Query("SELECT * FROM transactions WHERE id = :id")
    override suspend fun getById(id: UUID): TransactionEntity

    @Query("DELETE FROM transactions WHERE id = :id")
    suspend fun deleteById(id: UUID)

    @Transaction
    @Query("SELECT * FROM transactions WHERE $filterByDayType")
    suspend fun getDayTransactions(type: TransactionType, day: String): List<TransactionEntity>

    @Transaction
    @Query("SELECT * FROM transactions t WHERE $filterByMonthYearType")
    suspend fun getMonthTransactions(type: TransactionType, month: Int, year: Int): List<TransactionEntity>

    @Transaction
    @Query("SELECT * FROM transactions t WHERE $filterByYearType")
    suspend fun getYearTransactions(type: TransactionType, year: Int): List<TransactionEntity>

    @Transaction
    @Query("SELECT * FROM transactions WHERE $filterByDayType")
    suspend fun getDayTransactionsWithCategory(type: TransactionType, day: String): List<TransactionWithCategoryEntity>

    @Transaction
    @Query("SELECT * FROM transactions t WHERE $filterByMonthYearType")
    suspend fun getMonthTransactionsWithCategory(type: TransactionType, month: Int, year: Int): List<TransactionWithCategoryEntity>

    @Transaction
    @Query("SELECT * FROM transactions t WHERE $filterByYearType")
    suspend fun getYearTransactionsWithCategory(type: TransactionType, year: Int): List<TransactionWithCategoryEntity>

    @Query("SELECT * FROM transactions WHERE $filterByDayType AND categoryId == :categoryId")
    suspend fun getDayTransactionsByCategory(categoryId: UUID, type: TransactionType, day: String): List<TransactionEntity>

    @Query("SELECT * FROM transactions t WHERE $filterByMonthYearType AND categoryId == :categoryId")
    suspend fun getMonthTransactionsByCategory(
        categoryId: UUID, type: TransactionType, month: Int, year: Int
    ): List<TransactionEntity>

    @Query("SELECT * FROM transactions t WHERE $filterByYearType AND categoryId == :categoryId")
    suspend fun getYearTransactionsByCategory(categoryId: UUID, type: TransactionType, year: Int): List<TransactionEntity>

    @Query("SELECT SUM(amount) from transactions")
    suspend fun getPeriodTotal(): Double
}