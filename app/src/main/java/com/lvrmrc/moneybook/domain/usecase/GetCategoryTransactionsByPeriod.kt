package com.lvrmrc.moneybook.domain.usecase


import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.model.TransactionWithCategory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GetCategoryTransactionsByPeriod(
    private val transactionRepo: TransactionRepositoryImpl, private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(
        period: TransactionPeriod, transType: TransactionType, date: LocalDateTime = LocalDateTime.now()
    ): List<CategoryWithTransactions> = withContext(dispatcher) {

        var result: List<TransactionWithCategory> = emptyList()

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

        groupTransactionsByCategory(result.reversed())
    }


    private fun groupTransactionsByCategory(transactionWithCategories: List<TransactionWithCategory>): List<CategoryWithTransactions> {
        val categories: ArrayList<CategoryWithTransactions> =
            ArrayList(transactionWithCategories.map { it.category.toCategoryWithTransactions() }.distinctBy { it.label })

        transactionWithCategories.forEach { trans ->
            categories.first { it.label == trans.category.label }.transactions.add(trans.toTransaction())
        }

        categories.forEach {
            it.total = it.transactions.sumOf { t -> t.amount }
        }

        return categories.toList()
    }
}