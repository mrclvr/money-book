package com.lvrmrc.moneybook.presentation.ui.compose.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.domain.model.IconLabel
import com.lvrmrc.moneybook.presentation.ui.compose.screens.CategoriesLibraryScreen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.CategoryScreen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.CategoryTransactionsScreen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.HomeScreen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.IconsLibraryScreen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.TransactionScreen
import com.lvrmrc.moneybook.presentation.ui.compose.screens.TransactionsListScreen
import com.lvrmrc.moneybook.presentation.viewmodel.AppViewModel
import java.util.UUID

const val rootRoute = "ROOT"
val innerRootRoute = Screen.Home.route

@Composable
fun NavGraph() {
    val navController = LocalNavController.current

    NavHost(navController = navController, startDestination = innerRootRoute) {

        /**
         * Home (Expense/Income)
         */
        composable(route = Screen.Home.route) {
            HomeScreen()
        }

        /**
         * Transaction
         */
        composableFromBottom(
            route = "${Screen.Transaction.route}?transactionId={transactionId}",
            arguments = listOf(navArgument("transactionId") { nullable = true })
        ) { entry ->
            val categoryId = entry.savedStateHandle.get<UUID>("categoryId")
            val appVm = entry.viewModel<AppViewModel>(navController)
            TransactionScreen(categoryId, appVm)
        }

        /**
         * Transactions list
         */
        composableFromRight(
            route = Screen.TransactionsList.route
        ) { entry ->
            val appVm = entry.viewModel<AppViewModel>(navController)
            TransactionsListScreen(appVm)
        }

        /**
         * Category Details
         */
        composableFromBottom(
            route = "${Screen.CategoryTransactions.route}/{categoryId}"
        ) { entry ->
            val appVm = entry.viewModel<AppViewModel>(navController)
            CategoryTransactionsScreen(appVm)
        }

        /**
         * Categories Library
         */
        composableFromRight(
            route = Screen.CategoriesLibrary.route
        ) { entry ->
            val appVm = entry.viewModel<AppViewModel>(navController)
            CategoriesLibraryScreen(appVm)
        }

        /**
         * Category
         */
        composableFromBottom(
            route = "${Screen.Category.route}?categoryId={categoryId}", arguments = listOf(navArgument("categoryId") { nullable = true })
        ) { entry ->
            val selectedIcon = entry.savedStateHandle.get<IconLabel>("iconLabel")
            CategoryScreen(iconLabel = selectedIcon)
        }

        /**
         * Icons library
         */
        composableFromRight(
            route = Screen.IconsLibrary.route
        ) {
            IconsLibraryScreen()
        }
    }
}

/**
 * Extended composable function with transition from bottom to top
 */
fun NavGraphBuilder.composableFromBottom(
    route: String, arguments: List<NamedNavArgument> = emptyList(), content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(route = route, enterTransition = {
        slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(300))
    }, exitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(300)
        )
    }, popEnterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(300)
        )
    }, popExitTransition = {
        slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(300)
        )
    }, arguments = arguments
    ) {
        content(it)
    }
}

/**
 * Extended composable function with transition from right to left
 */
fun NavGraphBuilder.composableFromRight(
    route: String, arguments: List<NamedNavArgument> = emptyList(), content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(route = route, enterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(300)
        )
    }, exitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(300)
        )
    }, popEnterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(300)
        )
    }, popExitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(300)
        )
    }, arguments = arguments
    ) {
        content(it)
    }
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

/**
 * Navigates to route with save state options
 */
fun NavHostController.navigateDefault(routeName: String) {
    val navController = this

    navController.navigate(routeName) {
        navController.graph.route?.let { route ->
            popUpTo(route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}


