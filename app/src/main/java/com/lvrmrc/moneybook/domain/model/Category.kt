package com.lvrmrc.moneybook.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.UUID


data class Category(
    override val id: UUID,
    override val label: String,
    override val icon: ImageVector,
    override val color: Color,
    override val lightText: Boolean
) : CategoryInterface {
    fun toCategoryWithTransactions() = CategoryWithTransactions(id, label, icon, color, lightText)
}