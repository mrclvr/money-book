package com.lvrmrc.moneybook.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.UUID

/**
 * Implementation of category interface
 *
 * @see CategoryInterface
 */
data class Category(
    override val id: UUID,
    override val label: String,
    override val icon: ImageVector,
    override val color: Color,
    override val lightText: Boolean
) : CategoryInterface {

    /**
     * Converts Category to category with empty list of transactions
     */
    fun toCategoryWithTransactions() = CategoryWithTransactions(id, label, icon, color, lightText, ArrayList(), 0.0)
}