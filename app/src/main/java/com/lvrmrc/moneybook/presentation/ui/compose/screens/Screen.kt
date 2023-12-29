package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.outlined.EmojiSymbols
import androidx.compose.ui.graphics.vector.ImageVector
import com.lvrmrc.moneybook.data.defaultIcon

enum class ScreenName {
    HOME, TRANSACTION_EDIT, TRANSACTIONS_LIST, CATEGORY_TRANSACTIONS, CATEGORIES_LIBRARY, CATEGORY_EDIT, ICONS_LIBRARY
}

sealed class Screen(
    val route: String, val label: String, val icon: ImageVector = defaultIcon, val bottomBar: Boolean = true, val fab: Boolean = true
) {
    data object Home : Screen(ScreenName.HOME.name, "Home", Icons.Filled.Savings)
    data object Transaction : Screen(ScreenName.TRANSACTION_EDIT.name, "Transaction")
    data object TransactionsList : Screen(ScreenName.TRANSACTIONS_LIST.name, "Transactions list", Icons.Filled.Receipt)
    data object Category : Screen(ScreenName.CATEGORY_EDIT.name, "Category", Icons.Filled.PostAdd)
    data object CategoryTransactions : Screen(
        ScreenName.CATEGORY_TRANSACTIONS.name, "Transactions details"
    )

    data object CategoriesLibrary : Screen(
        ScreenName.CATEGORIES_LIBRARY.name, "Categories library", Icons.Filled.GridView
    )


    data object IconsLibrary : Screen(
        ScreenName.ICONS_LIBRARY.name, "Icons library", Icons.Outlined.EmojiSymbols
    )

//    companion object {
//        fun hasNavbar(route: String?): Boolean {
//            return Screen::class.sealedSubclasses.map { it.objectInstance as Screen }.singleOrNull { it.route == route }.let {
//                when (it) {
//                    null -> false
//                    else -> it.bottomBar
//                }
//            }
//        }
//
//        fun hasFAB(route: String?): Boolean {
//            return Screen::class.sealedSubclasses.map { it.objectInstance as Screen }.singleOrNull { it.route == route }.let {
//                when (it) {
//                    null -> false
//                    else -> it.fab
//                }
//            }
//        }
//    }
}