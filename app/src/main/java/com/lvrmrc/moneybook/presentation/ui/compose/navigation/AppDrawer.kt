package com.lvrmrc.moneybook.presentation.ui.compose.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val drawerItemColors = NavigationDrawerItemDefaults.colors(
        selectedContainerColor = colorScheme.primaryContainer,
        selectedIconColor = colorScheme.onPrimaryContainer,
        selectedTextColor = colorScheme.onPrimaryContainer
    )

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet(
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp, 24.dp, 8.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(36.dp), painter = painterResource(R.drawable.app_icon_no_bg), contentDescription = "App icon"
                )
                Text(
                    stringResource(R.string.app_name),
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }
            Divider()
            /**
             * Home
             */
            NavigationDrawerItem(modifier = Modifier.padding(4.dp),
                icon = { Icon(Screen.Home.icon, stringResource(R.string.open_home_screen)) },
                label = { Text(text = stringResource(R.string.home)) },
                colors = drawerItemColors,
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
             * All transactions
             */
            NavigationDrawerItem(modifier = Modifier.padding(4.dp),
                icon = { Icon(Screen.TransactionsList.icon, stringResource(R.string.open_transactions_screen)) },
                label = { Text(text = stringResource(R.string.all_transactions)) },
                colors = drawerItemColors,
                selected = navController.currentDestination?.route === Screen.TransactionsList.route,
                onClick = {
                    drawerScope.launch {
                        drawerState.apply {
                            close()
                        }
                    }
                    navController.navigateDefault(Screen.TransactionsList.route)
                })
            /**
             * Categories
             */
            NavigationDrawerItem(modifier = Modifier.padding(4.dp),
                icon = { Icon(Screen.CategoriesLibrary.icon, stringResource(R.string.open_categories_screen)) },
                label = { Text(text = stringResource(R.string.categories)) },
                colors = drawerItemColors,
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
                colors = drawerItemColors,
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
