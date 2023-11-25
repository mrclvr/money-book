package com.lvrmrc.moneybook.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lvrmrc.moneybook.ui.screens.ExpenseScreen
import com.lvrmrc.moneybook.ui.screens.IncomeScreen
import com.lvrmrc.moneybook.ui.screens.Screen
import com.lvrmrc.moneybook.ui.screens.TransactionScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            ExpenseScreen()
        }
        composable(route = Screen.Stats.route) {
            IncomeScreen()
        }
        composable(route = Screen.Transaction.route, enterTransition = {
            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Companion.Up, animationSpec = tween(300))
        }, popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Down, animationSpec = tween(300)
            )
        }) {
            TransactionScreen(navController = navController)
        }
    }

}



