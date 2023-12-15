package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Addchart
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.ui.graphics.vector.ImageVector

enum class ScreenName {
    HOME, TRANSACTION, TRANSACTIONS_DETAILS, CATEGORIES
}

//Aggiungere icona piena se selezionato
sealed class Screen(val route: String, val label: String, val icon: ImageVector, val bottomBar: Boolean = true, val fab: Boolean = true) {
    data object Home : Screen(ScreenName.HOME.name, "Home", Icons.Rounded.ArrowUpward, bottomBar = true)
    data object Transaction : Screen(ScreenName.TRANSACTION.name, "Transactions", Icons.Rounded.Add, bottomBar = false, fab = false)
    data object TransactionsDetails : Screen(
        ScreenName.TRANSACTIONS_DETAILS.name, "Transactions Details", Icons.Rounded.AccountBalanceWallet, bottomBar = false, fab = false
    )

    data object Categories : Screen(
        ScreenName.CATEGORIES.name, "Categories", Icons.Rounded.Addchart, bottomBar = false, fab = false
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