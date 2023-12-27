package com.lvrmrc.moneybook.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Today
import androidx.compose.ui.graphics.vector.ImageVector
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.data.source.db.entity.CategoryEntity
import com.lvrmrc.moneybook.domain.model.ColorName
import com.lvrmrc.moneybook.domain.model.IconLabel
import com.lvrmrc.moneybook.domain.model.IconStyle
import com.lvrmrc.moneybook.domain.model.TransPeriodTabItem
import com.lvrmrc.moneybook.domain.model.TransTypeTabItem
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.model.getIconsMap
import com.lvrmrc.moneybook.presentation.ui.theme.analogous1_500
import com.lvrmrc.moneybook.presentation.ui.theme.analogous2_500
import com.lvrmrc.moneybook.presentation.ui.theme.complementary300
import com.lvrmrc.moneybook.presentation.ui.theme.complementary500
import com.lvrmrc.moneybook.presentation.ui.theme.primary
import com.lvrmrc.moneybook.presentation.ui.theme.primary500
import com.lvrmrc.moneybook.presentation.ui.theme.triadic1_500
import com.lvrmrc.moneybook.presentation.ui.theme.triadic2_500
import java.util.UUID

val periodTabs: List<TransPeriodTabItem> = listOf(
    TransPeriodTabItem(
        title = R.string.day, icon = Icons.Filled.Today, period = TransactionPeriod.DAY
    ), TransPeriodTabItem(
        title = R.string.month, icon = Icons.Filled.CalendarMonth, period = TransactionPeriod.MONTH
    ), TransPeriodTabItem(
        title = R.string.year, icon = Icons.Filled.EditCalendar, period = TransactionPeriod.YEAR
    )
)

val transactionsTabs: List<TransTypeTabItem> = listOf(
    TransTypeTabItem(
        title = R.string.expense, icon = Icons.Filled.ArrowUpward, type = TransactionType.EXPENSE
    ),
    TransTypeTabItem(
        title = R.string.income, icon = Icons.Filled.ArrowDownward, type = TransactionType.INCOME
    ),
)

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

val defaultIcon = Icons.Filled.QuestionMark

val outlinedIcons: Map<IconLabel, ImageVector> = getIconsMap(IconStyle.Outlined)
//val defaultIcons: Map<IconLabel, ImageVector> = getIconsMap(IconStyle.Default)
//val filledIcons: Map<IconLabel, ImageVector> = getIconsMap(IconStyle.Filled)
//val roundedIcons: Map<IconLabel, ImageVector> = getIconsMap(IconStyle.Rounded)

val expenseCategoryEntities = listOf(
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Health",
        icon = IconLabel.MonitorHeart,
        type = TransactionType.EXPENSE,
        color = ColorName.Primary500,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Home",
        icon = IconLabel.House,
        type = TransactionType.EXPENSE,
        color = ColorName.Complementary500,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Education",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorName.AnalogousOne500,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Groceries",
        icon = IconLabel.ShoppingBasket,
        type = TransactionType.EXPENSE,
        color = ColorName.AnalogousTwo500,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Shopping",
        icon = IconLabel.ShoppingCart,
        type = TransactionType.EXPENSE,
        color = ColorName.TriadicOne500,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Car",
        icon = IconLabel.DirectionsCar,
        type = TransactionType.EXPENSE,
        color = ColorName.TriadicTwo500,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Gas",
        icon = IconLabel.LocalGasStation,
        type = TransactionType.EXPENSE,
        color = ColorName.Primary300,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Bills",
        icon = IconLabel.ReceiptLong,
        type = TransactionType.EXPENSE,
        color = ColorName.Complementary300,
        lightText = true,
    ),
)

val expenseCategories = expenseCategoryEntities.map { it.toDomain() }
