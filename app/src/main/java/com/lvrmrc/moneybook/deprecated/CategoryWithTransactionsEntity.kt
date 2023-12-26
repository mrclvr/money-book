package com.lvrmrc.moneybook.deprecated

import androidx.room.Embedded
import androidx.room.Relation
import com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
import com.lvrmrc.moneybook.data.source.db.entity.TransactionEntity
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions

data class CategoryWithTransactionsEntity(
    @Embedded val category: CategoryEntity, @Relation(
        entity = TransactionEntity::class, parentColumn = "id", entityColumn = "categoryId"
    ) val transactions: List<TransactionEntity>

) {
    fun toDomain(): CategoryWithTransactions {
        val (id, label, icon, color, type, lightText) = category.toDomain()
        return CategoryWithTransactions(id,
            label,
            icon,
            color,
            type,
            lightText,
            ArrayList(transactions.map { it.toDomain() }),
            transactions.sumOf { it.amount })
    }
}
