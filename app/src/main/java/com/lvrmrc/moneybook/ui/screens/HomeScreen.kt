package com.lvrmrc.moneybook.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.core.components.NavbarLayout

@Composable
fun HomeScreen(navController: NavHostController) {
    NavbarLayout(navController, content = {
        Box {
            Text(text = "Home", modifier = Modifier.clickable {
                navController.navigate(route = Screen.Home.route)
            })
        }
    })
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}