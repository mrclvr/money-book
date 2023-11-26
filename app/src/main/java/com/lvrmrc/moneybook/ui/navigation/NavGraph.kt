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
    val rootRoute = "ROOT"

    NavHost(navController = navController, startDestination = Screen.Home.route) {
//        navigation(startDestination = Screen.Home.route, route = rootRoute) {
        composable(route = Screen.Home.route) { entry ->

//            val appViewModel = entry.appViewModel<AppViewModel>(navController)


            ExpenseScreen()
        }
        composable(route = Screen.Stats.route) { entry ->
//            val appViewModel = entry.appViewModel<AppViewModel>(navController)
            IncomeScreen()
        }
        composable(route = Screen.Transaction.route, enterTransition = {
            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Companion.Up, animationSpec = tween(300))
        }, popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Down, animationSpec = tween(300)
            )
        }) { entry ->
//            val appViewModel = entry.appViewModel<AppViewModel>(navController)
            TransactionScreen(navController)
        }
//        }
    }

}

//@Composable
//inline fun <reified T : ViewModel> NavBackStackEntry.appViewModel(navController: NavHostController): T {
//    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
//    val parentEntry = remember(this) {
//        navController.getBackStackEntry(navGraphRoute)
//    }
//
//    return hiltViewModel(parentEntry)
//}


