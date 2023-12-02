package com.lvrmrc.moneybook.data.source.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.ColorValue
import com.lvrmrc.moneybook.domain.model.LabeledIcon
import com.lvrmrc.moneybook.domain.model.TransactionType
import java.util.UUID

@Entity(tableName = "categories")
data class CategoryEntity constructor(
    @PrimaryKey @ColumnInfo(name = "id") val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "label") val label: String,
    @ColumnInfo(name = "icon") val icon: LabeledIcon.Label,
    @ColumnInfo(name = "type") val type: TransactionType,
    @ColumnInfo(name = "color") val color: ColorValue.Name,
    @ColumnInfo(name = "deleted") val deleted: Boolean = false
) {
    fun toDomain(): Category {
        return Category(
            label = label, icon = LabeledIcon.getIcon(icon), color = ColorValue.getColorValue(color)
        )
    }
}

