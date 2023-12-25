package com.lvrmrc.moneybook.presentation.ui.compose.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.presentation.ui.compose.screens.CategoriesScreen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.CategoryDetailsScreen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.HomeScreen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.TransactionScreen
import com.lvrmrc.moneybook.presentation.viewmodel.AppViewModel

const val rootRoute = "ROOT"
val innerRootRoute = Screen.Home.route

@Composable
fun NavGraph() {
    val navController = LocalNavController.current

    NavHost(navController = navController, startDestination = innerRootRoute) {

//        navigation(startDestination = Screen.Home.route, route = rootRoute) {

        /**
         * Home (Expense/Income)
         */
        composable(route = Screen.Home.route) {

            HomeScreen()
        }

        /**
         * Transaction
         */
        composable(route = "${Screen.Transaction.route}?transactionId={transactionId}",
            arguments = listOf(navArgument("transactionId") { nullable = true }),
            enterTransition = {
                slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(300)
                )
            }) { entry ->
            val appVm = entry.viewModel<AppViewModel>(navController)
            TransactionScreen(appVm)
        }

        /**
         * Transactions Details
         */
        composable(route = "${Screen.CategoryDetails.route}/{categoryId}") { entry ->
            val appVm = entry.viewModel<AppViewModel>(navController)
            CategoryDetailsScreen(appVm)

        }

        /**
         * Categories
         */
        composable(route = Screen.Categories.route, enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700)
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700)
            )
        }, popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700)
            )
        }, popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700)
            )
        }) { entry ->
            val appVm = entry.viewModel<AppViewModel>(navController)
            CategoriesScreen(appVm)
        }
    }
//    }

}

/**
 * Gets shared ViewModel from parent entry
 */
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.viewModel(navController: NavHostController): T {
    val navGraphRoute = innerRootRoute ?: rootRoute ?: destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return hiltViewModel(parentEntry)
}


