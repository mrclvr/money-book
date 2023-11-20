package com.lvrmrc.moneybook.ui.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.screens.Screen
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme

@Composable
fun AppNavBar(navController: NavHostController) {
    val screensList = arrayListOf<Screen>()
    screensList.add(Screen.Home)
    screensList.add(Screen.Stats)

    BottomAppBar(
        actions = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination

            screensList.forEach { screen ->
                NavigationBarItem(
                    onClick = {
                        navController.navigate(screen.route) {

                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.DarkGray,
                        indicatorColor = Color.LightGray
                    ),
                    selected = currentRoute?.hierarchy?.any { it.route == screen.route } == true,
                    icon = { Icon(imageVector = screen.icon, contentDescription = screen.label) },
                    label = { Text(text = screen.label, fontSize = 9.sp) }
                )
//                        IconButton(onClick = { /* do something */ }) {
//                        Icon(
//                            Icons.Filled.Edit,
//                            contentDescription = "Localized description",
//                        )
//                    }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AppNavBarPreview() {
    MoneyBookTheme {
        AppNavBar(navController = rememberNavController())
    }
}




