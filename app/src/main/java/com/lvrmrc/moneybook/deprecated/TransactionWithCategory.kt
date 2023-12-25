package com.lvrmrc.moneybook.deprecated

import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.TransactionInterface
import com.lvrmrc.moneybook.domain.model.TransactionType
import java.time.LocalDateTime
import java.util.UUID


data class TransactionWithCategory(
    override val id: UUID,
    override val notes: String,
    override val amount: Double,
    override val date: LocalDateTime,
    override val type: TransactionType,
    override val categoryId: UUID,
    val category: Category,
) : TransactionInterface


