package com.lvrmrc.moneybook.data.source.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionWithCategoryEntity(
    @Embedded val transaction: TransactionEntity, @Relation(
        entity = CategoryEntity::class, parentColumn = "categoryId", entityColumn = "id"
    ) val category: CategoryEntity
)