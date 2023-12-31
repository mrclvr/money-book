package com.lvrmrc.moneybook.domain.usecase


import com.lvrmrc.moneybook.data.repository.TransactionRepositoryImpl
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.model.TransactionWithCategory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GetTransactionsByPeriod(
    private val transactionRepo: TransactionRepositoryImpl, private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    /**
     * Gets transactions from specific period with category
     *
     * @param period period of time
     * @param transType income or expense type
     */
    suspend operator fun invoke(
        period: TransactionPeriod, transType: TransactionType, date: LocalDateTime
    ): List<TransactionWithCategory> = withContext(dispatcher) {

        var result: List<TransactionWithCategory> = emptyList()

        when (period) {
            TransactionPeriod.DAY -> {
                result = transactionRepo.getDayTransactionsWithCategory(transType, date.format(DateTimeFormatter.BASIC_ISO_DATE))
            }

            TransactionPeriod.MONTH -> {
                result = transactionRepo.getMonthTransactionsWithCategory(transType, date.monthValue, date.year)
            }

            TransactionPeriod.YEAR -> {
                result = transactionRepo.getYearTransactionsWithCategory(transType, date.year)
            }

            else -> {}
        }

        result.reversed()
    }
}