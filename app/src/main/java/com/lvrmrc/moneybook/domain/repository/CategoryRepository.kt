package com.lvrmrc.moneybook.domain.repository

import com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.TransactionType
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 *
 * Collecting from the Flows in [CategoryDao] is main-safe.  Room supports Coroutines and moves the
 * query execution off of the main thread.
 */
@Singleton
interface CategoryRepository {

    suspend fun insert(category: CategoryEntity)

    suspend fun getYearCategoriesWithTransactions(type: TransactionType, year: Int): List<CategoryWithTransactions>

}
