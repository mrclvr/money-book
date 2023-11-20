package com.lvrmrc.moneybook.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.components.NavBarLayout
import com.lvrmrc.moneybook.viewmodels.HomeViewModel


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavHostController) {
    HomeScreen(navController = navController, total = viewModel.total)
}

@Composable
private fun HomeScreen(navController: NavHostController, total: Double) {
    NavBarLayout(navController, content = {
        Box {
            Text(text = total.toString(), modifier = Modifier.clickable {
                navController.navigate(route = Screen.Home.route)
            })
        }
    })
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}