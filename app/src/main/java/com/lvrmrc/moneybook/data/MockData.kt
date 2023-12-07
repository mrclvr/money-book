package com.lvrmrc.moneybook.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Today
import com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
import com.lvrmrc.moneybook.data.source.db.entity.TransactionEntity
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.ColorValue
import com.lvrmrc.moneybook.domain.model.LabeledIcon
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.presentation.ui.compose.components.tabs.TabItem
import java.time.LocalDateTime
import java.util.UUID

val mockCategoryEntities = listOf(
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "School",
        icon = LabeledIcon.Label.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Name.TEAL,
        lightText = false,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Test",
        icon = LabeledIcon.Label.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Name.PURPLE,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "School",
        icon = LabeledIcon.Label.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Name.TEAL,
        lightText = false,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Test",
        icon = LabeledIcon.Label.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Name.PURPLE,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "School",
        icon = LabeledIcon.Label.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Name.TEAL,
        lightText = false,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Test",
        icon = LabeledIcon.Label.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Name.PURPLE,
        lightText = true,
    ),
)

val mockCategories = mockCategoryEntities.map { it.toDomain() }

val mockCatTransactions = arrayListOf(
    CategoryWithTransactions(
        mockCategories[0].id,
        mockCategories[0].label,
        mockCategories[0].icon,
        mockCategories[0].color,
        mockCategories[0].lightText,
        arrayListOf(
            Transaction(
                id = UUID.randomUUID(), amount = 1.0, notes = "Food 1", type = TransactionType.EXPENSE, date = LocalDateTime.of(
                    2023, 2, 14, 12, 1
                )
            ), Transaction(
                id = UUID.randomUUID(),
                amount = 2.0,
                notes = "Health 2",
                type = TransactionType.EXPENSE,
                date = LocalDateTime.of(2023, 8, 25, 13, 1)
            ), Transaction(
                id = UUID.randomUUID(),
                amount = 3.0,
                notes = "Cinema 3",
                type = TransactionType.EXPENSE,
                date = LocalDateTime.of(2023, 11, 22, 14, 1)
            )
        ),
        6.0
    ), CategoryWithTransactions(
        mockCategories[1].id,
        mockCategories[1].label,
        mockCategories[1].icon,
        mockCategories[1].color,
        mockCategories[1].lightText,
        arrayListOf(
            Transaction(
                id = UUID.randomUUID(), amount = 1.0, notes = "Food 1", type = TransactionType.EXPENSE, date = LocalDateTime.of(
                    2023, 2, 14, 12, 1
                )
            ),
            Transaction(
                id = UUID.randomUUID(),
                amount = 2.0,
                notes = "Health 2",
                type = TransactionType.EXPENSE,
                date = LocalDateTime.of(2023, 8, 25, 13, 1)
            ),
            Transaction(
                id = UUID.randomUUID(),
                amount = 3.0,
                notes = "Cinema 3",
                type = TransactionType.EXPENSE,
                date = LocalDateTime.of(2023, 11, 22, 14, 1)
            ),
            Transaction(
                id = UUID.randomUUID(),
                amount = 4.0,
                notes = "Food 4",
                type = TransactionType.EXPENSE,
                date = LocalDateTime.of(2023, 11, 23, 15, 1)
            ),
            Transaction(
                id = UUID.randomUUID(),
                amount = 5.0,
                notes = "Health 5",
                type = TransactionType.EXPENSE,
                date = LocalDateTime.of(2023, 11, 24, 16, 1)
            ),
            Transaction(
                id = UUID.randomUUID(),
                amount = 6.0,
                notes = "Cinema 6",
                type = TransactionType.EXPENSE,
                date = LocalDateTime.of(2023, 11, 24, 17, 1)
            ),
        ),
        21.0
    )
)

val mockTransactionEntities = listOf(
    TransactionEntity(
        amount = 1.0, notes = "Food 1", type = TransactionType.EXPENSE, date = LocalDateTime.of(
            2023, 2, 14, 12, 1
        ), categoryId = mockCategoryEntities[0].id
    ),
    TransactionEntity(
        amount = 2.0,
        notes = "Health 2",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 8, 25, 13, 1),
        categoryId = mockCategoryEntities[0].id
    ),
    TransactionEntity(
        amount = 3.0,
        notes = "Cinema 3",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 22, 14, 1),
        categoryId = mockCategoryEntities[0].id
    ),
    TransactionEntity(
        amount = 4.0,
        notes = "Food 4",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 23, 15, 1),
        categoryId = mockCategoryEntities[1].id
    ),
    TransactionEntity(
        amount = 5.0,
        notes = "Health 5",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 24, 16, 1),
        categoryId = mockCategoryEntities[1].id
    ),
    TransactionEntity(
        amount = 6.0,
        notes = "Cinema 6",
        type = TransactionType.EXPENSE,
        date = LocalDateTime.of(2023, 11, 24, 17, 1),
        categoryId = mockCategoryEntities[1].id
    ),
)

val mockTransactions = mockTransactionEntities.map { it.toDomain() }

val mockPeriodTabs = listOf(
    TabItem(title = "Day", icon = Icons.Filled.Today),
    TabItem(title = "Month", icon = Icons.Filled.CalendarMonth),
    TabItem(title = "Year", icon = Icons.Filled.EditCalendar)
)