package com.lvrmrc.moneybook.data.repository

import com.lvrmrc.moneybook.data.source.db.dao.CategoryDao
import com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.TransactionType
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * CategoryRepository implementation
 */
@Singleton
class CategoryRepositoryImpl @Inject constructor(private val categoryDao: CategoryDao) : CategoryRepository {

    /**
     * Inserts new category
     *
     * @param category the category to insert
     */
    override suspend fun insert(category: CategoryEntity) = categoryDao.insert(category)

    /**
     * Gets category by its UUID
     *
     *  @param id the category's UUID
     */
    override suspend fun getById(id: UUID): Category = categoryDao.getById(id).toDomain()

    /**
     * Gets all categories
     *
     */
    override suspend fun getAll(): List<Category> = categoryDao.getAll().map { it.toDomain() }

    /**
     * Gets all categories of given type
     *
     */
    override suspend fun getAllOfType(transType: TransactionType): List<Category> =
        categoryDao.getAllOfType(transType).map { it.toDomain() }

//    companion object {
//        @Volatile
//        private var instance: CategoryRepositoryImpl? = null
//
//        fun getInstance(categoryDao: CategoryDao) = instance ?: synchronized(this) {
//            instance ?: CategoryRepositoryImpl(categoryDao).also { instance = it }
//        }
//    }
}
