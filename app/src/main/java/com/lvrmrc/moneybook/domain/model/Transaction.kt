package com.lvrmrc.moneybook.domain.model

import java.time.LocalDateTime
import java.util.UUID


data class Transaction(
    override val id: UUID,
    override var notes: String,
    override var amount: Double,
    override var date: LocalDateTime,
    override val type: TransactionType,
    override val categoryId: UUID? = null
) : TransactionInterface {

    fun toTransactionWithCategory(category: Category) = TransactionWithCategory(id, notes, amount, date, type, categoryId, category)
}


