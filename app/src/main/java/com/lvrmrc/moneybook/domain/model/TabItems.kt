package com.lvrmrc.moneybook.domain.model

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.lvrmrc.moneybook.R

/**
 * Transaction type tab item
 */
data class TransTypeTabItem(
    override val title: Int,
    override val icon: ImageVector,
    override val onClick: () -> Unit = {},
    override val content: @Composable () -> Unit = {},
    val type: TransactionType
) : TabItemInterface

/**
 * Transaction period tab item
 */
data class TransPeriodTabItem(
    override val title: Int,
    override val icon: ImageVector,
    override val onClick: () -> Unit = {},
    override val content: @Composable () -> Unit = {},
    val period: TransactionPeriod
) : TabItemInterface

/**
 * Custom tab indicator modifier
 */
fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition, tabWidth: Dp
): Modifier = composed {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth, animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing), label = "Current tab width"
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
        label = "Indicator offset"
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}

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
