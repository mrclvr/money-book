package com.lvrmrc.moneybook.domain.model

import java.time.LocalDateTime
import java.util.UUID


interface TransactionInterface {
    val id: UUID
    val notes: String
    val amount: Double
    val date: LocalDateTime
    val type: TransactionType
}


