package com.lvrmrc.moneybook.data.source.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.domain.model.TransactionType
import java.time.LocalDateTime
import java.util.UUID

@Entity(
    tableName = TransactionEntity.TABLE_NAME
)
data class TransactionEntity constructor(
    @PrimaryKey @ColumnInfo(name = "id") val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "notes") val notes: String = "",
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "date") val date: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = "type") val type: TransactionType,
    @ColumnInfo(name = "categoryId") val categoryId: UUID,
    @ColumnInfo(name = "deleted") val deleted: Boolean = false,

    ) {

    fun toDomain(): Transaction {
        return Transaction(
            notes = notes,
            amount = amount,
            date = date,
            type = type,
//            category = category.toDomain()
        )
    }

    companion object {
        const val TABLE_NAME = "transactions"
    }
}