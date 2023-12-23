package com.lvrmrc.moneybook.data

import com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
import com.lvrmrc.moneybook.domain.model.ColorValue
import com.lvrmrc.moneybook.domain.model.IconLabel
import com.lvrmrc.moneybook.domain.model.TransactionType

val expenseCategoryEntities = listOf(
    CategoryEntity(
        label = "Health",
        icon = IconLabel.MonitorHeart,
        type = TransactionType.EXPENSE,
        color = ColorValue.Name.Primary500,
        lightText = true,
    ),
    CategoryEntity(
        label = "Home",
        icon = IconLabel.House,
        type = TransactionType.EXPENSE,
        color = ColorValue.Name.Complementary500,
        lightText = true,
    ),
    CategoryEntity(
        label = "Education",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorValue.Name.AnalogousOne500,
        lightText = true,
    ),
    CategoryEntity(
        label = "Groceries",
        icon = IconLabel.ShoppingBasket,
        type = TransactionType.EXPENSE,
        color = ColorValue.Name.AnalogousTwo500,
        lightText = true,
    ),
    CategoryEntity(
        label = "Shopping",
        icon = IconLabel.ShoppingCart,
        type = TransactionType.EXPENSE,
        color = ColorValue.Name.TriadicOne500,
        lightText = true,
    ),
    CategoryEntity(
        label = "Car",
        icon = IconLabel.DirectionsCar,
        type = TransactionType.EXPENSE,
        color = ColorValue.Name.TriadicTwo500,
        lightText = true,
    ),
    CategoryEntity(
        label = "Gas",
        icon = IconLabel.LocalGasStation,
        type = TransactionType.EXPENSE,
        color = ColorValue.Name.Primary300,
        lightText = true,
    ),
    CategoryEntity(
        label = "Bills",
        icon = IconLabel.ReceiptLong,
        type = TransactionType.EXPENSE,
        color = ColorValue.Name.Complementary300,
        lightText = true,
    ),
)

val expenseCategories = expenseCategoryEntities.map { it.toDomain() }