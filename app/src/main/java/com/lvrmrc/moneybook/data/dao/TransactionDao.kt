package com.lvrmrc.moneybook.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.lvrmrc.moneybook.data.entity.Transaction

@Dao
interface TransactionDao : BaseDao<Transaction> {
    @Query("SELECT * FROM transactions")
    suspend fun getAll(): List<Transaction>

    @Query("SELECT * FROM transactions WHERE id IN (:transactionIds)")
    suspend fun getAllByIds(transactionIds: IntArray): List<Transaction>

    @Query("SELECT SUM(amount) from transactions")
    suspend fun getPeriodTotal(): Double
}