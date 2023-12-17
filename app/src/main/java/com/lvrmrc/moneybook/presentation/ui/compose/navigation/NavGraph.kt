package com.lvrmrc.moneybook.presentation.ui.compose.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.lvrmrc.moneybook.data.AppState
import com.lvrmrc.moneybook.presentation.ui.compose.screens.CategoriesScreen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.CategoryDetailsScreen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.ExpenseScreen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.TransactionScreen
import com.lvrmrc.moneybook.presentation.viewmodel.ExpenseViewModel

@Composable
fun NavGraph(navController: NavHostController) {

    val appState = AppState.getInstance()
    val rootRoute = "ROOT"

    NavHost(navController = navController, startDestination = rootRoute) {

//        composable(route = "Loading") { entry ->
//            if (!appState.loading) navController.popBackStack()
//
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(MaterialTheme.colorScheme.primary),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(modifier = Modifier.padding(0.dp, 10.dp), text = "Loading...")
//                CircularProgressIndicator()
//            }
//        }

        /**
         * Nested nav graph to share viewmodel
         */
        navigation(startDestination = Screen.Home.route, route = rootRoute) {

            /**
             * Expense/Income (Home)
             */
            composable(route = Screen.Home.route) { entry ->

                val parentEntry = remember(entry) {
                    navController.getBackStackEntry(rootRoute)
                }

                val vm = hiltViewModel<ExpenseViewModel>(parentEntry)

                ExpenseScreen(navController, vm)
            }

            /**
             * Transactions Details
             */
            composable(route = "${Screen.CategoryDetails.route}/{categoryId}") { entry ->

                val parentEntry = remember(entry) {
                    navController.getBackStackEntry(rootRoute)
                }

                val vm = hiltViewModel<ExpenseViewModel>(parentEntry)

                CategoryDetailsScreen(navController)

            }
        }

        /**
         * Transaction
         */
        composable(route = "${Screen.Transaction.route}?transactionId={transactionId}",
            arguments = listOf(navArgument("transactionId") { nullable = true }),
            enterTransition = {
                slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Companion.Up, animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Down, animationSpec = tween(300)
                )
            }) {
            TransactionScreen(navController)
        }

//            when (initialState.destination.route) {
//                Screen.Home.route ->
//            slideIntoContainer(
//                AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700)
//            )
//                else -> null
//            }

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
        }) {
            CategoriesScreen(navController)
        }
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


