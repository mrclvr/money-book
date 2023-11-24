package com.lvrmrc.moneybook.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class ScreenName {
    HOME, STATS, TRANS
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector, val bottomBarVisible: Boolean = false) {
    data object Home : Screen(ScreenName.HOME.name, "Home", Icons.Rounded.Home, bottomBarVisible = true)
    data object Stats : Screen(ScreenName.STATS.name, "Stats", Icons.Rounded.BarChart, bottomBarVisible = true)
    data object Transaction : Screen(ScreenName.TRANS.name, "Transactions", Icons.Rounded.Add)

    companion object {
        fun hasNavbar(route: String?): Boolean {
            return Screen::class.sealedSubclasses.map { it.objectInstance as Screen }.singleOrNull { it.route == route }.let {
                when (it) {
                    null -> false
                    else -> it.bottomBarVisible
                }
            }
        }
    }
}