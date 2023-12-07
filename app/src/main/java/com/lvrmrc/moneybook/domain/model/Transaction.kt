package com.lvrmrc.moneybook.domain.model

import java.time.LocalDateTime
import java.util.UUID


data class Transaction(
    override val id: UUID,
    override val notes: String,
    override val amount: Double,
    override val date: LocalDateTime,
    override val type: TransactionType
) : TransactionInterface


