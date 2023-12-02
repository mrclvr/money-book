package com.lvrmrc.moneybook.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.lvrmrc.moneybook.data.source.db.entity.TransactionEntity
import com.lvrmrc.moneybook.data.source.db.entity.TransactionWithCategoryEntity
import com.lvrmrc.moneybook.domain.model.TransactionType
import java.time.LocalDateTime


@Dao
interface TransactionDao : BaseDao<TransactionEntity> {

    @Query("SELECT * FROM transactions")
    suspend fun getAll(): List<TransactionEntity>

    @Transaction
    @Query("SELECT * FROM transactions")
    suspend fun getAllWithCategory(): List<TransactionWithCategoryEntity>

    @Query("SELECT * FROM transactions WHERE id IN (:transactionIds)")
    suspend fun getAllByIds(transactionIds: IntArray): List<TransactionEntity>

    @Query("SELECT * FROM transactions WHERE date = :day AND type = :type")
    suspend fun getDayTransactions(type: TransactionType, day: LocalDateTime): List<TransactionEntity>

    @Query("SELECT * FROM transactions t WHERE $filterByMonthYearType")
    suspend fun getMonthTransactions(type: TransactionType, month: Int, year: Int): List<TransactionEntity>

    @Transaction
    @Query("SELECT * FROM transactions t WHERE $filterByYearType")
    suspend fun getYearTransactions(type: TransactionType, year: Int): List<TransactionWithCategoryEntity>


    @Query("SELECT SUM(amount) from transactions")
    suspend fun getPeriodTotal(): Double


}