package com.lvrmrc.moneybook.presentation.ui.compose.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppDrawer(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    content: @Composable () -> Unit = {}
) {

    val drawerScope: CoroutineScope = rememberCoroutineScope()

    AppDrawer(drawerState = drawerState, content = content, onNavigate = {
        drawerScope.launch {
            drawerState.apply {
                close()
//                if (isClosed) open() else close()
            }
        }

        navController.navigate(it) {
            navController.graph.route?.let { route ->
                popUpTo(route) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    })
}

@Composable
private fun AppDrawer(
    drawerState: DrawerState, onNavigate: (String) -> Unit = {}, content: @Composable () -> Unit = {}
) {
    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet {
            Text("Money Book", modifier = Modifier.padding(16.dp))
            Divider()
            NavigationDrawerItem(label = { Text(text = "Categories") }, selected = false, onClick = {
                onNavigate(Screen.Categories.route)
            })
        }
    }) {
        content()
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AppDrawerPreview() {
    AppDrawer(drawerState = rememberDrawerState(DrawerValue.Open), onNavigate = {})
}
