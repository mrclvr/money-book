package com.lvrmrc.moneybook.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
import com.lvrmrc.moneybook.data.source.db.entity.CategoryWithTransactionsEntity
import com.lvrmrc.moneybook.domain.model.TransactionType

@Dao
interface CategoryDao : BaseDao<CategoryEntity> {
    @Query("SELECT * FROM categories")
    suspend fun getAll(): List<CategoryEntity>

//    @Transaction
//    @Query("SELECT * FROM categories WHERE id IN (:categoryId)")
//    fun getCategoryWithTransactions(categoryId: UUID): List<CategoryWithTransactionsEntity>
//
//    @Transaction
//    @Query("SELECT * FROM categories")
//    fun getCategoriesWithTransactions(): List<CategoryWithTransactionsEntity>

    @Transaction
    @Query(
        "SELECT c.* FROM categories c LEFT JOIN transactions t ON c.id = t.categoryId AND $filterByYearType GROUP BY c.id"
    )
    suspend fun getYearCategoriesWithTransactions(type: TransactionType, year: Int): List<CategoryWithTransactionsEntity>

}