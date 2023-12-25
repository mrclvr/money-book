package com.lvrmrc.moneybook.domain.usecase


import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

class GetTransactionsByPeriodAndCategory(
    private val transactionRepo: TransactionRepositoryImpl, private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    /**
     * Gets transactions from specific period and category
     *
     * @param categoryId id of category
     * @param period period of time
     * @param transType income or expense type
     */
    suspend operator fun invoke(
        categoryId: UUID, period: TransactionPeriod, transType: TransactionType
    ): List<Transaction> = withContext(dispatcher) {

        val date = LocalDateTime.now()
        var result: List<Transaction> = emptyList()

        when (period) {
            TransactionPeriod.DAY -> {
                result = transactionRepo.getDayTransactionsByCategory(categoryId, transType, date.format(DateTimeFormatter.BASIC_ISO_DATE))
            }

            TransactionPeriod.MONTH -> {
                result = transactionRepo.getMonthTransactionsByCategory(categoryId, transType, date.monthValue, date.year)
            }

            TransactionPeriod.YEAR -> {
                result = transactionRepo.getYearTransactionsByCategory(categoryId, transType, date.year)
            }

            else -> {}
        }

        result.reversed()
    }
}