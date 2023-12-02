package com.lvrmrc.moneybook.data.source.db.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions

data class CategoryWithTransactionsEntity(
    @Embedded val category: CategoryEntity, @Relation(
        entity = TransactionEntity::class, parentColumn = "id", entityColumn = "categoryId"
    ) val transactions: List<TransactionEntity>
) {
    fun toDomain(): CategoryWithTransactions {
        val (label, icon, color) = category.toDomain()
        return CategoryWithTransactions(label, icon, color, transactions.map { it.toDomain() })
    }
}