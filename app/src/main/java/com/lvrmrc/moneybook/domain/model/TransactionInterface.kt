package com.lvrmrc.moneybook.domain.model

import java.time.LocalDateTime


interface TransactionInterface {
    val notes: String
    val amount: Double
    val date: LocalDateTime
    val type: TransactionType
}


