package com.lvrmrc.moneybook.presentation.ui.compose.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme

@Composable
fun BottomBarLayout(
    navController: NavHostController = rememberNavController(), content: @Composable () -> Unit
) {
    val bottomBarVisible = rememberSaveable { (mutableStateOf(true)) }
    val fabVisible = rememberSaveable { (mutableStateOf(true)) }
    val snackBarHostState = remember { SnackbarHostState() }
    val screensList = arrayListOf(Screen.Expense, Screen.Income)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: NavDestination? = navBackStackEntry?.destination
//    var bottomBarVisible = true

//    if (navBackStackEntry?.destination?.route != null) {
//        fabVisible.value = Screen.hasFAB(currentRoute?.route)
////        bottomBarVisible = Screen.hasNavbar(currentRoute?.route)
//    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
//            if (bottomBarVisible)
            AnimatedAppNavBar(navController, screensList, bottomBarVisible.value)
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            AnimatedAppFAB(navController, fabVisible.value)
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
                .background(colorScheme.primary),
        ) {
            content()
        }
    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        bottomBarVisible.value = Screen.hasNavbar(destination.route)
        fabVisible.value = Screen.hasFAB(destination.route)
    }
}


@Preview()
@Composable
fun BottomBarLayoutPreview() {
    MoneyBookTheme {
        BottomBarLayout {}
    }
}