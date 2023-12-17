package com.lvrmrc.moneybook.presentation.ui.compose.components.tabs

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.TransactionType

data class TabItem(
    override val title: String,
    override val icon: ImageVector,
    override val content: @Composable () -> Unit = {},
    override val onClick: suspend () -> Unit = {}
) : TabItemInterface

data class TransTypeTabItem(
    override val title: String,
    override val icon: ImageVector,
    override val content: @Composable () -> Unit = {},
    override val onClick: suspend () -> Unit = {},
    val type: TransactionType
) : TabItemInterface

data class PeriodTabItem(
    override val title: String,
    override val icon: ImageVector,
    override val content: @Composable () -> Unit = {},
    override val onClick: suspend () -> Unit = {},
    val period: TransactionPeriod
) : TabItemInterface


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
