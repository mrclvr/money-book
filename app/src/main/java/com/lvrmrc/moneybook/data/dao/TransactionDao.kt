package com.lvrmrc.moneybook.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lvrmrc.moneybook.data.entity.Transaction

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions")
    fun getAll(): List<Transaction>

    @Query("SELECT * FROM transactions WHERE id IN (:transactionIds)")
    fun getAllByIds(transactionIds: IntArray): List<Transaction>

    @Query("SELECT SUM(amount) from transactions")
    fun getPeriodTotal(): Double

    @Insert
    fun insertAll(vararg transactions: Transaction)

    @Delete
    fun delete(transaction: Transaction)
}