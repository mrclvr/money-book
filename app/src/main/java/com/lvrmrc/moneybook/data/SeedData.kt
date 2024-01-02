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
import com.lvrmrc.moneybook.presentation.ui.theme.aqua1
import com.lvrmrc.moneybook.presentation.ui.theme.aqua2
import com.lvrmrc.moneybook.presentation.ui.theme.aqua3
import com.lvrmrc.moneybook.presentation.ui.theme.aqua4
import com.lvrmrc.moneybook.presentation.ui.theme.blue1
import com.lvrmrc.moneybook.presentation.ui.theme.blue2
import com.lvrmrc.moneybook.presentation.ui.theme.blue3
import com.lvrmrc.moneybook.presentation.ui.theme.blue4
import com.lvrmrc.moneybook.presentation.ui.theme.fuchsia1
import com.lvrmrc.moneybook.presentation.ui.theme.fuchsia2
import com.lvrmrc.moneybook.presentation.ui.theme.fuchsia3
import com.lvrmrc.moneybook.presentation.ui.theme.fuchsia4
import com.lvrmrc.moneybook.presentation.ui.theme.green1
import com.lvrmrc.moneybook.presentation.ui.theme.green2
import com.lvrmrc.moneybook.presentation.ui.theme.green3
import com.lvrmrc.moneybook.presentation.ui.theme.green4
import com.lvrmrc.moneybook.presentation.ui.theme.orange1
import com.lvrmrc.moneybook.presentation.ui.theme.orange2
import com.lvrmrc.moneybook.presentation.ui.theme.orange3
import com.lvrmrc.moneybook.presentation.ui.theme.orange4
import com.lvrmrc.moneybook.presentation.ui.theme.purple1
import com.lvrmrc.moneybook.presentation.ui.theme.purple2
import com.lvrmrc.moneybook.presentation.ui.theme.purple3
import com.lvrmrc.moneybook.presentation.ui.theme.purple4
import com.lvrmrc.moneybook.presentation.ui.theme.red1
import com.lvrmrc.moneybook.presentation.ui.theme.red2
import com.lvrmrc.moneybook.presentation.ui.theme.red3
import com.lvrmrc.moneybook.presentation.ui.theme.red4
import com.lvrmrc.moneybook.presentation.ui.theme.watermelon1
import com.lvrmrc.moneybook.presentation.ui.theme.watermelon2
import com.lvrmrc.moneybook.presentation.ui.theme.watermelon3
import com.lvrmrc.moneybook.presentation.ui.theme.watermelon4
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
    ColorName.ORANGE_1 to orange1,
    ColorName.ORANGE_2 to orange2,
    ColorName.ORANGE_3 to orange3,
    ColorName.ORANGE_4 to orange4,
    ColorName.RED_1 to red1,
    ColorName.RED_2 to red2,
    ColorName.RED_3 to red3,
    ColorName.RED_4 to red4,
    ColorName.WATERMELON_1 to watermelon1,
    ColorName.WATERMELON_2 to watermelon2,
    ColorName.WATERMELON_3 to watermelon3,
    ColorName.WATERMELON_4 to watermelon4,
    ColorName.FUCHSIA_1 to fuchsia1,
    ColorName.FUCHSIA_2 to fuchsia2,
    ColorName.FUCHSIA_3 to fuchsia3,
    ColorName.FUCHSIA_4 to fuchsia4,
    ColorName.PURPLE_1 to purple1,
    ColorName.PURPLE_2 to purple2,
    ColorName.PURPLE_3 to purple3,
    ColorName.PURPLE_4 to purple4,
    ColorName.BLUE_1 to blue1,
    ColorName.BLUE_2 to blue2,
    ColorName.BLUE_3 to blue3,
    ColorName.BLUE_4 to blue4,
    ColorName.AQUA_1 to aqua1,
    ColorName.AQUA_2 to aqua2,
    ColorName.AQUA_3 to aqua3,
    ColorName.AQUA_4 to aqua4,
    ColorName.GREEN_1 to green1,
    ColorName.GREEN_2 to green2,
    ColorName.GREEN_3 to green3,
    ColorName.GREEN_4 to green4,
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
        color = ColorName.RED_2,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Home",
        icon = IconLabel.House,
        type = TransactionType.EXPENSE,
        color = ColorName.AQUA_3,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Education",
        icon = IconLabel.School,
        type = TransactionType.EXPENSE,
        color = ColorName.FUCHSIA_2,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Groceries",
        icon = IconLabel.ShoppingBasket,
        type = TransactionType.EXPENSE,
        color = ColorName.GREEN_2,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Food",
        icon = IconLabel.LunchDining,
        type = TransactionType.EXPENSE,
        color = ColorName.ORANGE_3,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Drinks",
        icon = IconLabel.LocalBar,
        type = TransactionType.EXPENSE,
        color = ColorName.WATERMELON_2,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Shopping",
        icon = IconLabel.ShoppingCart,
        type = TransactionType.EXPENSE,
        color = ColorName.PURPLE_3,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Car",
        icon = IconLabel.DirectionsCar,
        type = TransactionType.EXPENSE,
        color = ColorName.BLUE_3,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Gas",
        icon = IconLabel.LocalGasStation,
        type = TransactionType.EXPENSE,
        color = ColorName.AQUA_4,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Bills",
        icon = IconLabel.ReceiptLong,
        type = TransactionType.EXPENSE,
        color = ColorName.PURPLE_1,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Sport",
        icon = IconLabel.FitnessCenter,
        type = TransactionType.EXPENSE,
        color = ColorName.ORANGE_4,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Technology",
        icon = IconLabel.VideogameAsset,
        type = TransactionType.EXPENSE,
        color = ColorName.BLUE_2,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Transportation",
        icon = IconLabel.Train,
        type = TransactionType.EXPENSE,
        color = ColorName.AQUA_1,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Travels",
        icon = IconLabel.FlightTakeoff,
        type = TransactionType.EXPENSE,
        color = ColorName.GREEN_1,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Shows",
        icon = IconLabel.LocalActivity,
        type = TransactionType.EXPENSE,
        color = ColorName.WATERMELON_1,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Other",
        icon = IconLabel.DEFAULT,
        type = TransactionType.EXPENSE,
        color = ColorName.PURPLE_3,
        lightText = true,
    ),
)

val incomeCategoryEntities = listOf(
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Salary",
        icon = IconLabel.RequestPage,
        type = TransactionType.INCOME,
        color = ColorName.GREEN_4,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Interests",
        icon = IconLabel.AccountBalance,
        type = TransactionType.INCOME,
        color = ColorName.BLUE_3,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Investments",
        icon = IconLabel.AccountBalanceWallet,
        type = TransactionType.INCOME,
        color = ColorName.WATERMELON_2,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Gift",
        icon = IconLabel.Redeem,
        type = TransactionType.INCOME,
        color = ColorName.ORANGE_2,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Savings",
        icon = IconLabel.Savings,
        type = TransactionType.INCOME,
        color = ColorName.AQUA_3,
        lightText = true,
    ),
    CategoryEntity(
        id = UUID.randomUUID(),
        label = "Other",
        icon = IconLabel.DEFAULT,
        type = TransactionType.INCOME,
        color = ColorName.PURPLE_3,
        lightText = true,
    ),
)

val expenseCategories = expenseCategoryEntities.map { it.toDomain() }
val incomeCategories = incomeCategoryEntities.map { it.toDomain() }
