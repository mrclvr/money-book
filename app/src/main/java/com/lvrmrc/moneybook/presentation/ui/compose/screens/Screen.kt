package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.ui.graphics.vector.ImageVector

enum class ScreenName {
    EXPENSE, INCOME, TRANSACTION
}

//Aggiungere icona piena se selezionato
sealed class Screen(val route: String, val label: String, val icon: ImageVector, val bottomBar: Boolean = true, val fab: Boolean = true) {
    data object Expense : Screen(ScreenName.EXPENSE.name, "Expense", Icons.Rounded.ArrowUpward, bottomBar = true)
    data object Income : Screen(ScreenName.INCOME.name, "Income", Icons.Rounded.ArrowDownward, bottomBar = true)
    data object Transaction : Screen(ScreenName.TRANSACTION.name, "Transactions", Icons.Rounded.Add, bottomBar = true, fab = false)

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