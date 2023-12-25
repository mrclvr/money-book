package com.lvrmrc.moneybook.data.source.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.domain.model.TransactionType
import java.time.LocalDateTime
import java.util.UUID

/**
 * Monetary transaction
 *
 * @param id
 * @param notes eventual notes
 * @param amount amount as double
 * @param date date with time
 * @param type expense or income
 * @param categoryId id of linked category
 */
@Entity(
    tableName = TransactionEntity.TABLE_NAME
)
data class TransactionEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: UUID,
    @ColumnInfo(name = "notes") val notes: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "date") val date: LocalDateTime,
    @ColumnInfo(name = "type") val type: TransactionType,
    @ColumnInfo(name = "categoryId") val categoryId: UUID,
) {
    companion object {
        const val TABLE_NAME = "transactions"
    }

    /**
     * Converts transaction entity object to domain object
     */
    fun toDomain(): Transaction {
        return Transaction(id, notes, amount, date, type, categoryId)
    }

}