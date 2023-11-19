package com.lvrmrc.moneybook.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class ScreenName {
    HOME,
    STATS,
    TRANS
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen(route = ScreenName.HOME.name, label = "Home", icon = Icons.Rounded.Home)
    object Stats : Screen(route = ScreenName.STATS.name, label = "Stats", icon = Icons.Rounded.BarChart)
    object Transaction : Screen(route = ScreenName.TRANS.name, label = "Transactions", icon = Icons.Rounded.Add)
}