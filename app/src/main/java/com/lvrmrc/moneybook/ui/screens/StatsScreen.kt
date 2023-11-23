package com.lvrmrc.moneybook.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.lvrmrc.moneybook.ui.layouts.BottomBarLayout

@Composable
fun StatsScreen(
    navController: NavHostController
) {
    BottomBarLayout(navController, content = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Stats",
                modifier = Modifier.clickable { navController.navigate(route = Screen.Stats.route) })
        }
    })
}