package com.lvrmrc.moneybook.data

import com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
import com.lvrmrc.moneybook.domain.model.ColorName
import com.lvrmrc.moneybook.domain.model.IconLabel
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.presentation.ui.theme.analogous1_500
import com.lvrmrc.moneybook.presentation.ui.theme.analogous2_500
import com.lvrmrc.moneybook.presentation.ui.theme.complementary300
import com.lvrmrc.moneybook.presentation.ui.theme.complementary500
import com.lvrmrc.moneybook.presentation.ui.theme.primary
import com.lvrmrc.moneybook.presentation.ui.theme.primary500
import com.lvrmrc.moneybook.presentation.ui.theme.triadic1_500
import com.lvrmrc.moneybook.presentation.ui.theme.triadic2_500


val colorsMap = mapOf(
    ColorName.Primary300 to primary,
    ColorName.Primary500 to primary500,
    ColorName.Complementary300 to complementary300,
    ColorName.Complementary500 to complementary500,
    ColorName.AnalogousOne500 to analogous1_500,
    ColorName.AnalogousTwo500 to analogous2_500,
    ColorName.TriadicOne500 to triadic1_500,
    ColorName.TriadicTwo500 to triadic2_500,
)

val expenseCategoryEntities = listOf(
    CategoryEntity(
        label = "Health",
        icon = IconLabel.MonitorHeart,
        type = TransactionType.EXPENSE,
        color = ColorName.Primary500,
        lightText = true,
    ),
    CategoryEntity(
        label = "Home",
        icon = IconLabel.House,
        type = TransactionType.EXPENSE,
        color = ColorName.Complementary500,
        lightText = true,
    ),
    CategoryEntity(
        label = "Education",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorName.AnalogousOne500,
        lightText = true,
    ),
    CategoryEntity(
        label = "Groceries",
        icon = IconLabel.ShoppingBasket,
        type = TransactionType.EXPENSE,
        color = ColorName.AnalogousTwo500,
        lightText = true,
    ),
    CategoryEntity(
        label = "Shopping",
        icon = IconLabel.ShoppingCart,
        type = TransactionType.EXPENSE,
        color = ColorName.TriadicOne500,
        lightText = true,
    ),
    CategoryEntity(
        label = "Car",
        icon = IconLabel.DirectionsCar,
        type = TransactionType.EXPENSE,
        color = ColorName.TriadicTwo500,
        lightText = true,
    ),
    CategoryEntity(
        label = "Gas",
        icon = IconLabel.LocalGasStation,
        type = TransactionType.EXPENSE,
        color = ColorName.Primary300,
        lightText = true,
    ),
    CategoryEntity(
        label = "Bills",
        icon = IconLabel.ReceiptLong,
        type = TransactionType.EXPENSE,
        color = ColorName.Complementary300,
        lightText = true,
    ),
)

val expenseCategories = expenseCategoryEntities.map { it.toDomain() }