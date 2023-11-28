package com.lvrmrc.moneybook.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.lvrmrc.moneybook.data.entity.Transaction
import com.lvrmrc.moneybook.data.entity.TransactionType
import java.time.LocalDate

@Dao
interface TransactionDao : BaseDao<Transaction> {
    @Query("SELECT * FROM transactions")
    suspend fun getAll(): List<Transaction>

    @Query("SELECT * FROM transactions WHERE id IN (:transactionIds)")
    suspend fun getAllByIds(transactionIds: IntArray): List<Transaction>

    @Query("SELECT * FROM transactions WHERE date = :day AND type = :type")
    suspend fun getDayTransactions(type: TransactionType, day: LocalDate): List<Transaction>

    @Query("SELECT * FROM transactions WHERE CAST(strftime('%m', date) AS integer) = :month AND CAST(strftime('%Y', date) AS integer) = :year AND type = :type")
    suspend fun getMonthTransactions(type: TransactionType, month: Int, year: Int): List<Transaction>

    @Query("SELECT * FROM transactions WHERE CAST(strftime('%Y', date) AS integer) = :year AND type = :type")
    suspend fun getYearTransactions(type: TransactionType, year: Int): List<Transaction>

    @Query("SELECT SUM(amount) from transactions")
    suspend fun getPeriodTotal(): Double
}