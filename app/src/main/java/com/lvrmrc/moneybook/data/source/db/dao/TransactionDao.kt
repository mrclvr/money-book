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

    @Query("SELECT * FROM transactions WHERE $filterByDayType")
    suspend fun getDayTransactions(type: TransactionType, day: String): List<TransactionWithCategoryEntity>

    @Query("SELECT * FROM transactions t WHERE $filterByMonthYearType")
    suspend fun getMonthTransactions(type: TransactionType, month: Int, year: Int): List<TransactionWithCategoryEntity>

    @Transaction
    @Query("SELECT * FROM transactions t WHERE $filterByYearType")
    suspend fun getYearTransactions(type: TransactionType, year: Int): List<TransactionWithCategoryEntity>

    @Query("SELECT SUM(amount) from transactions")
    suspend fun getPeriodTotal(): Double


}