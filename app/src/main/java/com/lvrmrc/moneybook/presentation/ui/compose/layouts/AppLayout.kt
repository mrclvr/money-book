package com.lvrmrc.moneybook.presentation.ui.compose.layouts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme


@Composable
fun AppLayout(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    content: @Composable () -> Unit = {}
) {
    MoneyBookTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
                ModalDrawerSheet {
                    Text("Money Book", modifier = Modifier.padding(16.dp))
                    Divider()
                    NavigationDrawerItem(label = { Text(text = "Drawer Item") }, selected = false, onClick = { /*TODO*/ })
                }
            }) {
                TabsLayout(navController = navController, content = content)
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppLayoutPreview() {
    AppLayout() {}
}