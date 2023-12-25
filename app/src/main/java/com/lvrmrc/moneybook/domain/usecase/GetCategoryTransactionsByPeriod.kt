package com.lvrmrc.moneybook.domain.usecase

import com.lvrmrc.moneybook.data.repository.CategoryRepositoryImpl
import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

class GetCategoryTransactionsByPeriod(
    private val transactionRepo: TransactionRepositoryImpl,
    private val categoryRepo: CategoryRepositoryImpl,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    /**
     * Gets transactions from specific period into category-grouped list
     *
     * @param period period of time
     * @param transType income or expense type
     */
    suspend operator fun invoke(
        period: TransactionPeriod, transType: TransactionType
    ): List<CategoryWithTransactions> = withContext(dispatcher) {

        val date = LocalDateTime.now()
        var result: List<Transaction> = emptyList()

        when (period) {
            TransactionPeriod.DAY -> {
                result = transactionRepo.getDayTransactions(transType, date.format(DateTimeFormatter.BASIC_ISO_DATE))
            }

            TransactionPeriod.MONTH -> {
                result = transactionRepo.getMonthTransactions(transType, date.monthValue, date.year)
            }

            TransactionPeriod.YEAR -> {
                result = transactionRepo.getYearTransactions(transType, date.year)
            }

            else -> {}
        }

        groupTransactionsByCategory(result, transType)
    }

    /**
     * Gets distinct categories from list of transactions and groups them into categories with transactions
     */
    private suspend fun groupTransactionsByCategory(
        transactions: List<Transaction>, transType: TransactionType
    ): List<CategoryWithTransactions> {
        val categoryIds: List<UUID> = transactions.map { it.categoryId }.distinct()
        val categories: ArrayList<CategoryWithTransactions> = getUsedCategoriesWithTransactions(categoryIds, transType)

        transactions.forEach { trans ->
            categories.firstOrNull { cat ->
                cat.id == trans.categoryId
            }?.transactions?.add(trans)
        }

        categories.forEach {
            it.total = it.transactions.sumOf { t -> t.amount }
        }

        return categories.sortedBy { it.total }.reversed().toList()
    }

    /**
     * Maps list of category ids to array list of distinct categories with transactions
     */
    private suspend fun getUsedCategoriesWithTransactions(
        ids: List<UUID>, transType: TransactionType
    ): ArrayList<CategoryWithTransactions> {
        val allCategories: List<Category> = categoryRepo.getAllOfType(transType)
        val usedCategories: List<Category> = allCategories.filter { it.id in ids }
        return ArrayList(usedCategories.map { it.toCategoryWithTransactions() })
    }
}