package com.lvrmrc.moneybook.ui.core.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.screens.Screen

@Composable
fun AppNavBar(navController: NavHostController) {
    val screensList = arrayListOf<Screen>()
    screensList.add(Screen.Home)
    screensList.add(Screen.Stats)

    BottomAppBar(
        actions = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination

            screensList.forEach { menuItem ->
                NavigationBarItem(
                    onClick = {
                        navController.navigate(menuItem.route) {

                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    selected =  currentRoute?.hierarchy?.any { it.route == it.route } == true,
                    icon = { Icon(imageVector = menuItem.icon, contentDescription = menuItem.label) },
                    label = { Text(text = menuItem.label, fontSize = 9.sp) }
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
     AppNavBar(navController = rememberNavController())
}




