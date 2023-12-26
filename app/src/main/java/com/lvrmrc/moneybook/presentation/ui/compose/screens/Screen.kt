package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.outlined.EmojiSymbols
import androidx.compose.ui.graphics.vector.ImageVector
import com.lvrmrc.moneybook.domain.model.defaultIcon

enum class ScreenName {
    HOME, TRANSACTION, TRANSACTIONS_DETAILS, CATEGORIES, CATEGORY, ICONS_LIBRARY
}

sealed class Screen(
    val route: String, val label: String, val icon: ImageVector = defaultIcon, val bottomBar: Boolean = true, val fab: Boolean = true
) {
    data object Home : Screen(ScreenName.HOME.name, "Home", Icons.Filled.Savings)
    data object Transaction : Screen(ScreenName.TRANSACTION.name, "Transaction")
    data object CategoryDetails : Screen(
        ScreenName.TRANSACTIONS_DETAILS.name, "Transactions details"
    )

    data object Categories : Screen(
        ScreenName.CATEGORIES.name, "Categories", Icons.Filled.GridView
    )

    data object Category : Screen(ScreenName.CATEGORY.name, "Category", Icons.Filled.PostAdd)

    data object IconsLibrary : Screen(
        ScreenName.ICONS_LIBRARY.name, "Icons", Icons.Outlined.EmojiSymbols
    )

    companion object {
        fun hasNavbar(route: String?): Boolean {
            return Screen::class.sealedSubclasses.map { it.objectInstance as Screen }.singleOrNull { it.route == route }.let {
                when (it) {
                    null -> false
                    else -> it.bottomBar
                }
            }
        }

        fun hasFAB(route: String?): Boolean {
            return Screen::class.sealedSubclasses.map { it.objectInstance as Screen }.singleOrNull { it.route == route }.let {
                when (it) {
                    null -> false
                    else -> it.fab
                }
            }
        }
    }
}