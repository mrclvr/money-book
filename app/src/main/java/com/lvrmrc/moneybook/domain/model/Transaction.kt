package com.lvrmrc.moneybook.domain.model

import java.time.LocalDateTime


data class Transaction(
    val notes: String, val amount: Double, val date: LocalDateTime, val type: TransactionType,
//    val category: Category
)


