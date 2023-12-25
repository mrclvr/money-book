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

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "label") val label: String,
    @ColumnInfo(name = "icon") val icon: IconLabel,
    @ColumnInfo(name = "type") val type: TransactionType,
    @ColumnInfo(name = "color") val color: ColorName,
    @ColumnInfo(name = "lightText") val lightText: Boolean,
) {
    fun toDomain(): Category {
        return Category(
            id, label, outlinedIcons.getOrDefault(icon, Icons.Outlined.QuestionMark), colorsMap.getOrDefault(color, primary), lightText
        )
    }
}

