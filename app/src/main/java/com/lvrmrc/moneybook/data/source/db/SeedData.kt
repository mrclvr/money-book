package com.lvrmrc.moneybook.data.source.db

import com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
import com.lvrmrc.moneybook.data.source.db.entity.TransactionEntity
import com.lvrmrc.moneybook.domain.model.ColorValue
import com.lvrmrc.moneybook.domain.model.LabeledIcon
import com.lvrmrc.moneybook.domain.model.TransactionType
import java.time.LocalDateTime
import java.util.UUID

val defaultCategories = arrayOf(
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "School",
        icon = LabeledIcon.Label.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Name.TEAL
    )
)

val defaultTransactions = arrayOf(
    TransactionEntity(
        amount = 1.0,
        notes = "Food 1",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 2, 14, 12, 1),
        categoryId = defaultCategories[0].id
    ),
    TransactionEntity(
        amount = 2.0,
        notes = "Health 2",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 8, 25, 12, 1),
        categoryId = defaultCategories[0].id
    ),
    TransactionEntity(
        amount = 3.0,
        notes = "Cinema 3",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 22, 12, 1),
        categoryId = defaultCategories[0].id
    ),
    TransactionEntity(
        amount = 4.0,
        notes = "Food 4",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 23, 12, 1),
        categoryId = defaultCategories[0].id
    ),
    TransactionEntity(
        amount = 5.0,
        notes = "Health 5",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 24, 12, 1),
        categoryId = defaultCategories[0].id
    ),
    TransactionEntity(
        amount = 6.0,
        notes = "Cinema 6",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 24, 12, 1),
        categoryId = defaultCategories[0].id
    ),
)