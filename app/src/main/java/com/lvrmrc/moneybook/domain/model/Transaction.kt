package com.lvrmrc.moneybook.domain.model

import java.time.LocalDateTime


data class Transaction(
    override val notes: String, override val amount: Double, override val date: LocalDateTime, override val type: TransactionType
) : TransactionInterface


