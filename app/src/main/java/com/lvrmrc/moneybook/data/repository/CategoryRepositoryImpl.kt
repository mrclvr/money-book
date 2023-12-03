package com.lvrmrc.moneybook.data.repository

import com.lvrmrc.moneybook.data.source.db.dao.CategoryDao
import com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.repository.CategoryRepository
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 *
 * Collecting from the Flows in [CategoryDao] is main-safe.  Room supports Coroutines and moves the
 * query execution off of the main thread.
 */
@Singleton
class CategoryRepositoryImpl @Inject constructor(private val categoryDao: CategoryDao) : CategoryRepository {

    override suspend fun insert(category: CategoryEntity) = categoryDao.insert(category)

    override suspend fun getDayCategoriesWithTransactions(type: TransactionType, day: LocalDateTime): List<CategoryWithTransactions> =
        categoryDao.getDayCategoriesWithTransactions(type, day).map { it.toDomain() }

    override suspend fun getMonthCategoriesWithTransactions(type: TransactionType, month: Int, year: Int): List<CategoryWithTransactions> =
        categoryDao.getMonthCategoriesWithTransactions(type, month, year).map { it.toDomain() }

    override suspend fun getYearCategoriesWithTransactions(type: TransactionType, year: Int): List<CategoryWithTransactions> =
        categoryDao.getYearCategoriesWithTransactions(type, year).map { it.toDomain() }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: CategoryRepositoryImpl? = null

        fun getInstance(categoryDao: CategoryDao) = instance ?: synchronized(this) {
            instance ?: CategoryRepositoryImpl(categoryDao).also { instance = it }
        }
    }
}
