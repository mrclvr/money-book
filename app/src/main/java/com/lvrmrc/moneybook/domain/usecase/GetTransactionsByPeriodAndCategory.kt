package com.lvrmrc.moneybook.domain.usecase


import com.lvrmrc.moneybook.data.AppState
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
    private val transactionRepo: TransactionRepositoryImpl,
    private val appState: AppState,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(
        categoryId: UUID,
        period: TransactionPeriod = appState.period,
        transType: TransactionType = appState.transType,
        date: LocalDateTime = LocalDateTime.now()
    ): List<Transaction> = withContext(dispatcher) {

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