package com.lvrmrc.moneybook.domain.model

import java.time.LocalDateTime


data class TransactionWithCategory(
    override val notes: String,
    override val amount: Double,
    override val date: LocalDateTime,
    override val type: TransactionType,
    val category: Category
) : TransactionInterface {

    fun toTransaction() = Transaction(notes, amount, date, type)
}


