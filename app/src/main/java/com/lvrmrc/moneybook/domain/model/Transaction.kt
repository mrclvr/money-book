package com.lvrmrc.moneybook.domain.model

import com.lvrmrc.moneybook.data.source.db.entity.TransactionEntity
import java.time.LocalDateTime
import java.util.UUID


data class Transaction(
    override var id: UUID,
    override var notes: String,
    override var amount: Double,
    override var date: LocalDateTime,
    override var type: TransactionType,
    override var categoryId: UUID
) : TransactionInterface {


    fun toEntity() = TransactionEntity(id, notes, amount, date, type, categoryId)


    fun toTransactionWithCategory(category: Category) = TransactionWithCategory(id, notes, amount, date, type, categoryId, category)
}


enum class TransactionType {
    EXPENSE, INCOME
}

val transTypeIntMap = mapOf(
    TransactionType.EXPENSE to 0, TransactionType.INCOME to 1
)

enum class TransactionPeriod {
    DAY, SPAN, WEEK, MONTH, YEAR,
}

val periodIntMap = mapOf(
    TransactionPeriod.DAY to 0, TransactionPeriod.MONTH to 1, TransactionPeriod.YEAR to 2
)


