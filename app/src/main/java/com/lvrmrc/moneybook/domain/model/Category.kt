package com.lvrmrc.moneybook.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
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
    override val type: TransactionType,
    override val lightText: Boolean
) : CategoryInterface {

    /**
     * Maps transaction instance to database entity
     */
    fun toEntity() = CategoryEntity(id, label, getIconLabel(outlinedIcons, icon), getColorName(color), type, lightText)

    /**
     * Converts Category to category with empty list of transactions
     */
    fun toCategoryWithTransactions() = CategoryWithTransactions(id, label, icon, color, type, lightText, ArrayList(), 0.0)
}