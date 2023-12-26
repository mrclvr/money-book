package com.lvrmrc.moneybook.data

import com.lvrmrc.moneybook.data.source.db.entity.TransactionEntity
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.domain.model.TransactionType
import java.time.LocalDateTime
import java.util.UUID

val mockCatTransactions = arrayListOf(
    CategoryWithTransactions(
        expenseCategories[0].id,
        expenseCategories[0].label,
        expenseCategories[0].icon,
        expenseCategories[0].color,
        TransactionType.EXPENSE,
        expenseCategories[0].lightText,
        arrayListOf(
            Transaction(
                id = UUID.randomUUID(), amount = 1.0, notes = "Food 1", type = TransactionType.EXPENSE, date = LocalDateTime.of(
                    2023, 2, 14, 12, 1
                ), categoryId = expenseCategories[0].id
            ), Transaction(
                id = UUID.randomUUID(),
                amount = 2.0,
                notes = "Health 2",
                type = TransactionType.EXPENSE,
                date = LocalDateTime.of(2023, 8, 25, 13, 1),
                categoryId = expenseCategories[0].id
            ), Transaction(
                id = UUID.randomUUID(),
                amount = 3.0,
                notes = "Cinema 3",
                type = TransactionType.EXPENSE,
                date = LocalDateTime.of(2023, 11, 22, 14, 1),
                categoryId = expenseCategories[0].id
            )
        ),
        6.0
    ), CategoryWithTransactions(
        expenseCategories[1].id,
        expenseCategories[1].label,
        expenseCategories[1].icon,
        expenseCategories[1].color,
        TransactionType.EXPENSE,
        expenseCategories[1].lightText,
        arrayListOf(
            Transaction(
                id = UUID.randomUUID(), amount = 1.0, notes = "Food 1", type = TransactionType.EXPENSE, date = LocalDateTime.of(
                    2023, 2, 14, 12, 1
                ), categoryId = expenseCategories[1].id
            ),
            Transaction(
                id = UUID.randomUUID(),
                amount = 2.0,
                notes = "Health 2",
                type = TransactionType.EXPENSE,
                date = LocalDateTime.of(2023, 8, 25, 13, 1),
                categoryId = expenseCategories[1].id
            ),
            Transaction(
                id = UUID.randomUUID(),
                amount = 3.0,
                notes = "Cinema 3",
                type = TransactionType.EXPENSE,
                date = LocalDateTime.of(2023, 11, 22, 14, 1),
                categoryId = expenseCategories[1].id
            ),
            Transaction(
                id = UUID.randomUUID(),
                amount = 4.0,
                notes = "Food 4",
                type = TransactionType.EXPENSE,
                date = LocalDateTime.of(2023, 11, 23, 15, 1),
                categoryId = expenseCategories[1].id
            ),
            Transaction(
                id = UUID.randomUUID(),
                amount = 5.0,
                notes = "Health 5",
                type = TransactionType.EXPENSE,
                date = LocalDateTime.of(2023, 11, 24, 16, 1),
                categoryId = expenseCategories[1].id
            ),
            Transaction(
                id = UUID.randomUUID(),
                amount = 6.0,
                notes = "Cinema 6",
                type = TransactionType.EXPENSE,
                date = LocalDateTime.of(2023, 11, 24, 17, 1),
                categoryId = expenseCategories[1].id
            ),
        ),
        21.0
    )
)

val mockTransactionEntities = listOf(
    TransactionEntity(
        id = UUID.randomUUID(), amount = 1.0, notes = "Food 1", type = TransactionType.EXPENSE, date = LocalDateTime.of(
            2023, 2, 14, 12, 1
        ), categoryId = expenseCategoryEntities[0].id
    ),
    TransactionEntity(
        id = UUID.randomUUID(),
        amount = 2.0,
        notes = "Health 2",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 8, 25, 13, 1),
        categoryId = expenseCategoryEntities[0].id
    ),
    TransactionEntity(
        id = UUID.randomUUID(),
        amount = 3.0,
        notes = "Cinema 3",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 22, 14, 1),
        categoryId = expenseCategoryEntities[0].id
    ),
    TransactionEntity(
        id = UUID.randomUUID(),
        amount = 4.0,
        notes = "Food 4",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 23, 15, 1),
        categoryId = expenseCategoryEntities[1].id
    ),
    TransactionEntity(
        id = UUID.randomUUID(),
        amount = 5.0,
        notes = "Health 5",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 24, 16, 1),
        categoryId = expenseCategoryEntities[1].id
    ),
    TransactionEntity(
        id = UUID.randomUUID(),
        amount = 6.0,
        notes = "Cinema 6",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 24, 17, 1),
        categoryId = expenseCategoryEntities[1].id
    ),
)

val mockTransactions = mockTransactionEntities.map { it.toDomain() }