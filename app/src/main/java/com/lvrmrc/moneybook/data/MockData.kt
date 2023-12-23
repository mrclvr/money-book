package com.lvrmrc.moneybook.data

import com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
import com.lvrmrc.moneybook.data.source.db.entity.TransactionEntity
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.ColorValue
import com.lvrmrc.moneybook.domain.model.IconLabel
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.domain.model.TransactionType
import java.time.LocalDateTime
import java.util.UUID

val mockCategoryEntities = listOf(
    CategoryEntity(
        id = UUID.fromString("88cceb78-188d-4576-a96a-5f6eaa89b97c"),
        label = "School",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Triadic500.name,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.fromString("74641143-4e1f-488c-ae2b-6b4de38faaad"),
        label = "Test",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Triadic500.name,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.fromString("14d75d52-7450-489d-a882-6f9260ab37ff"),
        label = "School",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Triadic500.name,
        lightText = false,
    ),
    CategoryEntity(
        id = UUID.fromString("b0dac7e2-c48a-4da5-816c-791e585cf43b"),
        label = "Test",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Triadic500.name,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.fromString("cbeb67bc-8571-42bb-8d9c-7cdd8b57b04e"),
        label = "School",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Triadic500.name,
        lightText = false,
    ),
    CategoryEntity(
        id = UUID.fromString("5757edd4-4ea0-4a5c-936b-d094c2a9bb26"),
        label = "Test",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Triadic500.name,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.fromString("5757edd4-4ea0-4a5c-936b-d094c2a9bb21"),
        label = "Test",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Triadic500.name,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.fromString("5757edd4-4ea0-4a5c-936b-d094c2a9bb22"),
        label = "Test",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Triadic500.name,
        lightText = false,
    ),
    CategoryEntity(
        id = UUID.fromString("5757edd4-4ea0-4a5c-936b-d094c2a9bb23"),
        label = "Test",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Triadic500.name,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.fromString("5757edd4-4ea0-4a5c-936b-d094c2a9bb24"),
        label = "Test",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Triadic500.name,
        lightText = false,
    ),
    CategoryEntity(
        id = UUID.fromString("5757edd4-4ea0-4a5c-936b-d094c2a9bb25"),
        label = "Test",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Triadic500.name,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.fromString("5757edd4-4ea0-4a5c-936b-d094c2a9bb26"),
        label = "Test",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Triadic500.name,
        lightText = false,
    ),
    CategoryEntity(
        id = UUID.fromString("5757edd4-4ea0-4a5c-936b-d094c2a9bb27"),
        label = "Test",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Triadic500.name,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.fromString("5757edd4-4ea0-4a5c-936b-d094c2a9bb28"),
        label = "Test",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Triadic500.name,
        lightText = false,
    ),
)

val mockCategories = mockCategoryEntities.map { it.toDomain() }

val mockCatTransactions = arrayListOf(
    CategoryWithTransactions(
        expenseCategories[0].id,
        expenseCategories[0].label,
        expenseCategories[0].icon,
        expenseCategories[0].color,
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
        amount = 1.0, notes = "Food 1", type = TransactionType.EXPENSE, date = LocalDateTime.of(
            2023, 2, 14, 12, 1
        ), categoryId = expenseCategoryEntities[0].id
    ),
    TransactionEntity(
        amount = 2.0,
        notes = "Health 2",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 8, 25, 13, 1),
        categoryId = expenseCategoryEntities[0].id
    ),
    TransactionEntity(
        amount = 3.0,
        notes = "Cinema 3",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 22, 14, 1),
        categoryId = expenseCategoryEntities[0].id
    ),
    TransactionEntity(
        amount = 4.0,
        notes = "Food 4",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 23, 15, 1),
        categoryId = expenseCategoryEntities[1].id
    ),
    TransactionEntity(
        amount = 5.0,
        notes = "Health 5",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 24, 16, 1),
        categoryId = expenseCategoryEntities[1].id
    ),
    TransactionEntity(
        amount = 6.0,
        notes = "Cinema 6",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 24, 17, 1),
        categoryId = expenseCategoryEntities[1].id
    ),
)

val mockTransactions = mockTransactionEntities.map { it.toDomain() }