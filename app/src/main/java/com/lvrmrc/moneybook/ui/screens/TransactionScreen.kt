package com.lvrmrc.moneybook.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.core.components.TopbarLayout

@Composable
fun TransactionScreen(
    navController: NavHostController
) {
    TopbarLayout(navController, Screen.Transaction.label, content = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Transaction",
                modifier = Modifier.clickable { navController.navigate(route = Screen.Transaction.route) })
        }
    })
}

@Preview
@Composable
fun TransactionScreenPreview() {
    TransactionScreen(navController = rememberNavController())
}