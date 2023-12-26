package com.lvrmrc.moneybook.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.UUID

/**
 * Implementation of category interface with list of transactions and total amount
 *
 * @see CategoryInterface
 */
data class CategoryWithTransactions(
    override val id: UUID,
    override val label: String,
    override val icon: ImageVector,
    override val color: Color,
    override val type: TransactionType,
    override val lightText: Boolean,
    var transactions: ArrayList<Transaction>,
    var total: Double
) : CategoryInterface
