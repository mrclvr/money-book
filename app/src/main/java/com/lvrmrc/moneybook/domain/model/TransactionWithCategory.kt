package com.lvrmrc.moneybook.domain.model

import com.lvrmrc.moneybook.data.source.db.entity.TransactionEntity
import java.time.LocalDateTime
import java.util.UUID


data class TransactionWithCategory(
    override val id: UUID,
    override val notes: String,
    override val amount: Double,
    override val date: LocalDateTime,
    override val type: TransactionType,
    val category: Category
) : TransactionInterface {
    fun toTransaction() = Transaction(id, notes, amount, date, type)
    fun toEntity() = TransactionEntity(id, notes, amount, date, type, category.id)
}


