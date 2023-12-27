package com.lvrmrc.moneybook.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
import com.lvrmrc.moneybook.domain.model.TransactionType
import java.util.UUID

@Dao
interface CategoryDao : BaseDao<CategoryEntity> {
    @Query("SELECT * FROM categories")
    suspend fun getAll(): List<CategoryEntity>

    @Query("SELECT * FROM categories WHERE type = :transType")
    suspend fun getAllOfType(transType: TransactionType): List<CategoryEntity>

    @Query("SELECT * FROM categories WHERE id = :id")
    override suspend fun getById(id: UUID): CategoryEntity

    @Query("DELETE FROM categories WHERE id = :id")
    suspend fun deleteById(id: UUID)

//    @Transaction
//    @Query("SELECT c.* FROM categories c INNER JOIN (SELECT * FROM transactions WHERE $filterByDayType) t ON c.id = t.categoryId GROUP BY c.id")
//    suspend fun getDayCategoriesWithTransactions(type: TransactionType, day: String): List<CategoryWithTransactionsEntity>
//
//    @Transaction
//    @Query("SELECT c.* FROM categories c INNER JOIN (SELECT * FROM transactions WHERE $filterByMonthYearType) t ON c.id = t.categoryId  GROUP BY c.id")
//    suspend fun getMonthCategoriesWithTransactions(type: TransactionType, month: Int, year: Int): List<CategoryWithTransactionsEntity>
//
//    @Transaction
//    @Query(
//        "SELECT c.* FROM categories c INNER JOIN (SELECT * FROM transactions WHERE $filterByYearType) t ON c.id = t.categoryId GROUP BY c.id"
//    )
//    suspend fun getYearCategoriesWithTransactions(type: TransactionType, year: Int): List<CategoryWithTransactionsEntity>

}