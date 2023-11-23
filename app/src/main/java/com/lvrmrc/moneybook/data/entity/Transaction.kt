package com.lvrmrc.moneybook.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity(tableName = "transactions")
data class Transaction constructor(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "date") val date: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "notes") val notes: String = "",

    )

