package com.lvrmrc.moneybook.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,

    @ColumnInfo(name = "amount") val amount: Double,

    @ColumnInfo(name = "notes") val notes: String,

    @ColumnInfo(name = "timestamp") val timestamp: Int,
)