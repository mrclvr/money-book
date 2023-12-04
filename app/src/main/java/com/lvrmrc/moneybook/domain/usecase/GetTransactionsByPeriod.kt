package com.lvrmrc.moneybook.domain.usecase


import com.lvrmrc.moneybook.data.AppState
import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.TransactionWithCategory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GetTransactionsByPeriod(
    private val transactionRepo: TransactionRepositoryImpl,
    private val appState: AppState,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(
        date: LocalDateTime = LocalDateTime.now()
    ): List<CategoryWithTransactions> = withContext(dispatcher) {
        
        var result: List<TransactionWithCategory> = emptyList()

        when (appState.period) {
            "Day" -> {
                result = transactionRepo.getDayTransactions(appState.transType, date.format(DateTimeFormatter.BASIC_ISO_DATE))
            }

            "Month" -> {
                result = transactionRepo.getMonthTransactions(appState.transType, date.monthValue, date.year)
            }

            "Year" -> {
                result = transactionRepo.getYearTransactions(appState.transType, date.year)
            }

            else -> {}
        }

        groupTransactionsByCategory(result.reversed())
    }


    private fun groupTransactionsByCategory(transactions: List<TransactionWithCategory>): List<CategoryWithTransactions> {
        val categories: ArrayList<CategoryWithTransactions> =
            ArrayList(transactions.map { it.category.toCategoryWithTransactions() }.distinctBy { it.label })

        transactions.forEach { trans ->
            categories.first { it.label == trans.category.label }.transactions.add(trans.toTransaction())
        }

        categories.forEach {
            it.total = it.transactions.sumOf { t -> t.amount }
        }

        return categories.toList()
    }
}