package com.lvrmrc.moneybook.data.repository

import com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.TransactionType
import java.util.UUID
import javax.inject.Singleton

/**
 * Repository module for handling data operations on Category entities.
 */
@Singleton
interface CategoryRepository {
    suspend fun insert(category: CategoryEntity)
    suspend fun getById(id: UUID): Category
    suspend fun getAll(): List<Category>
    suspend fun getAllOfType(transType: TransactionType): List<Category>
}
