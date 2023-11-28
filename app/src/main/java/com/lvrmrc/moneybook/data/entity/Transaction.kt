package com.lvrmrc.moneybook.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

enum class TransactionType {
    EXPENSE, INCOME
}

@Entity(tableName = "transactions")
data class Transaction constructor(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "date") val date: LocalDate = LocalDate.now(),
    @ColumnInfo(name = "type") val type: TransactionType,
    @ColumnInfo(name = "notes") val notes: String = "",

    )

