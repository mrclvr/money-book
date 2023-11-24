package com.lvrmrc.moneybook.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.lvrmrc.moneybook.data.entity.Transaction
import java.time.LocalDate

@Dao
interface TransactionDao : BaseDao<Transaction> {
    @Query("SELECT * FROM transactions")
    suspend fun getAll(): List<Transaction>

    @Query("SELECT * FROM transactions WHERE id IN (:transactionIds)")
    suspend fun getAllByIds(transactionIds: IntArray): List<Transaction>

    @Query("SELECT * FROM transactions WHERE date = :day")
    suspend fun getDayTransactions(day: LocalDate): List<Transaction>

    @Query("SELECT * FROM transactions WHERE CAST(strftime('%m', date) AS integer) = :month AND CAST(strftime('%Y', date) AS integer) = :year")
    suspend fun getMonthTransactions(month: Int, year: Int): List<Transaction>

    @Query("SELECT * FROM transactions WHERE CAST(strftime('%Y', date) AS integer) = :year")
    suspend fun getYearTransactions(year: Int): List<Transaction>

    @Query("SELECT SUM(amount) from transactions")
    suspend fun getPeriodTotal(): Double
}