package com.lvrmrc.moneybook.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class ScreenName {
    HOME,
    STATS,
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen(route = ScreenName.HOME.name, label = "Home", icon = Icons.Filled.Home)
    object Stats : Screen(route = ScreenName.STATS.name, label = "Stats", icon = Icons.Filled.Home)
}