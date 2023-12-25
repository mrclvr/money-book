package com.lvrmrc.moneybook.domain.model

import java.time.LocalDateTime
import java.util.UUID

/**
 * Domain transposition of transaction entity
 *
 * @see com.lvrmrc.moneybook.data.source.db.entity.TransactionEntity
 */
interface TransactionInterface {
    val id: UUID
    val notes: String
    val amount: Double
    val date: LocalDateTime
    val type: TransactionType
    val categoryId: UUID
}


