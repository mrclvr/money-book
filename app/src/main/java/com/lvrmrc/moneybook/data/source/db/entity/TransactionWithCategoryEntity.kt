package com.lvrmrc.moneybook.data.source.db.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.lvrmrc.moneybook.domain.model.TransactionWithCategory

data class TransactionWithCategoryEntity(
    @Embedded val transaction: TransactionEntity, @Relation(
        entity = CategoryEntity::class, parentColumn = "categoryId", entityColumn = "id"
    ) val category: CategoryEntity
) {
    fun toDomain(): TransactionWithCategory {
        val (id, notes, amount, date, type, categoryId) = transaction.toDomain()
        return TransactionWithCategory(id, notes, amount, date, type, categoryId, category.toDomain())
    }
}