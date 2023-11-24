package com.lvrmrc.moneybook.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.components.AnimatedAppNavBar
import com.lvrmrc.moneybook.ui.navigation.NavGraph
import com.lvrmrc.moneybook.ui.screens.Screen
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme


@Composable
fun BottomBarLayout(
    content: @Composable() () -> Unit = {}
) {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
//    val coroutineScope = rememberCoroutineScope()
    val screensList = arrayListOf<Screen>()
    screensList.add(Screen.Home)
    screensList.add(Screen.Stats)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: NavDestination? = navBackStackEntry?.destination
    var bottomBarVisible = true

    if (navBackStackEntry?.destination?.route != null) {
        bottomBarVisible = Screen.hasNavbar(currentRoute?.route)
    }
    println("VISIBLE" + bottomBarVisible)

    Surface(
        modifier = Modifier.fillMaxSize(), color = colorScheme.background
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { AnimatedAppNavBar(navController, screensList, bottomBarVisible) },
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.offset(0.dp, 50.dp), shape = CircleShape, onClick = {
//                    coroutineScope.launch {
//                        viewModel.addExpense(
//                            Transaction(
//                                amount = 5.55, title = "Pippo", type = "Expense"
//                            )
//                        )
//
//                    }
                        navController.navigate(Screen.Transaction.route) {

                            navController.graph.route?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }, elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                ) {
                    Icon(Screen.Transaction.icon, Screen.Transaction.label)
                }
            },
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            },
        ) {
            Box(
                modifier = Modifier
                    .padding(paddingValues = it)
                    .background(colorScheme.primary),
            ) {
                NavGraph(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarLayoutPreview() {
    MoneyBookTheme {
        BottomBarLayout()
    }
}