package com.lvrmrc.moneybook.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.components.AppNavBar
import com.lvrmrc.moneybook.ui.screens.Screen
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarLayout(navController: NavHostController, content: @Composable() () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = { AppNavBar(navController = navController) },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.offset(0.dp, 50.dp), shape = CircleShape, onClick = {
                    navController.navigate(Screen.Transaction.route) {

                        navController.graph.startDestinationRoute?.let { route ->
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
        }) {
        Box(
            modifier = Modifier
                .padding(paddingValues = it)
                .background(colorScheme.primary),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        25.dp, 50.dp
                    ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavBarLayoutPreview() {
    MoneyBookTheme {
        BottomBarLayout(rememberNavController(), content = {})
    }
}