package com.lvrmrc.moneybook.presentation.ui.compose.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lvrmrc.moneybook.presentation.ui.compose.screens.ExpenseScreen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.TransactionScreen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.TransactionsDetailsScreen
import com.lvrmrc.moneybook.presentation.viewmodel.ExpenseViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    val rootRoute = "ROOT"

    NavHost(navController = navController, startDestination = rootRoute) {
        navigation(startDestination = Screen.Home.route, route = rootRoute) {
            composable(route = Screen.Home.route) { entry ->

                val parentEntry = remember(entry) {
                    navController.getBackStackEntry(rootRoute)
                }

                val vm = hiltViewModel<ExpenseViewModel>(parentEntry)

                ExpenseScreen(navController, vm)
            }
            composable(route = Screen.TransactionsDetails.route) { entry ->

                val parentEntry = remember(entry) {
                    navController.getBackStackEntry(rootRoute)
                }

                val vm = hiltViewModel<ExpenseViewModel>(parentEntry)

                TransactionsDetailsScreen(vm)
            }
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


