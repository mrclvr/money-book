package com.lvrmrc.moneybook.presentation.ui.compose.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun AppDrawer(
    drawerState: DrawerState, content: @Composable () -> Unit = {}
) {
    val navController = LocalNavController.current
    val drawerScope: CoroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet(
//            drawerContainerColor = colorScheme.primaryContainer
        ) {
            Text(stringResource(R.string.app_name), modifier = Modifier.padding(16.dp))
            Divider()
            /**
             * Home
             */
            NavigationDrawerItem(modifier = Modifier.padding(4.dp),
                icon = { Icon(Screen.Home.icon, stringResource(R.string.open_home_screen)) },
                label = { Text(text = stringResource(R.string.home)) },
                selected = navController.currentDestination?.route === Screen.Home.route,
                onClick = {
                    drawerScope.launch {
                        drawerState.apply {
                            close()
//                if (isClosed) open() else close()
                        }
                    }
                    navController.navigateDefault(Screen.Home.route)
                })
            /**
             * Categories
             */
            NavigationDrawerItem(modifier = Modifier.padding(4.dp),
                icon = { Icon(Screen.CategoriesLibrary.icon, stringResource(R.string.open_categories_screen)) },
                label = { Text(text = stringResource(R.string.categories)) },
                selected = navController.currentDestination?.route === Screen.CategoriesLibrary.route,
                onClick = {
                    drawerScope.launch {
                        drawerState.apply {
                            close()
//                if (isClosed) open() else close()
                        }
                    }
                    navController.navigateDefault(Screen.CategoriesLibrary.route)
                })
            /**
             * Icons
             */
            NavigationDrawerItem(modifier = Modifier.padding(4.dp),
                icon = { Icon(Screen.IconsLibrary.icon, stringResource(R.string.open_icons_screen)) },
                label = { Text(text = stringResource(R.string.icons)) },
                selected = navController.currentDestination?.route === Screen.IconsLibrary.route,
                onClick = {
                    drawerScope.launch {
                        drawerState.apply {
                            close()
                        }
                    }
                    navController.navigateDefault(Screen.IconsLibrary.route)
                })
        }
    }) {
        content()
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AppDrawerPreview() {
    NavProvider {
        AppDrawer(drawerState = rememberDrawerState(DrawerValue.Open))
    }
}
