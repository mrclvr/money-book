package com.lvrmrc.moneybook.data.source.db.entity

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lvrmrc.moneybook.data.colorsMap
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.ColorName
import com.lvrmrc.moneybook.domain.model.IconLabel
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.model.outlinedIcons
import com.lvrmrc.moneybook.presentation.ui.theme.primary
import java.util.UUID

/**
 * Category of expense or income
 *
 * @param id
 * @param label name
 * @param icon  icon name
 * @param type  type (expense or income)
 * @param color color name
 * @param lightText whether to use light text color or not
 */
@Entity(tableName = CategoryEntity.TABLE_NAME)
data class CategoryEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: UUID,
    @ColumnInfo(name = "label") val label: String,
    @ColumnInfo(name = "icon") val icon: IconLabel,
    @ColumnInfo(name = "type") val type: TransactionType,
    @ColumnInfo(name = "color") val color: ColorName,
    @ColumnInfo(name = "lightText") val lightText: Boolean,
) {
    companion object {
        const val TABLE_NAME = "categories"
    }

    /**
     * Converts category entity object to domain object
     */
    fun toDomain(): Category {
        return Category(
            id, label, outlinedIcons.getOrDefault(icon, Icons.Outlined.QuestionMark), colorsMap.getOrDefault(color, primary), lightText
        )
    }
}

