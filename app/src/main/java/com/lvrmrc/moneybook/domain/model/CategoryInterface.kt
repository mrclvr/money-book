package com.lvrmrc.moneybook.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.UUID

/**
 * Domain transposition of category entity
 *
 * @see com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
 */
interface CategoryInterface {
    val id: UUID
    val label: String
    val icon: ImageVector
    val color: Color
    val lightText: Boolean
}