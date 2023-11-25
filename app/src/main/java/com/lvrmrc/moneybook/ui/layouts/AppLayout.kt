package com.lvrmrc.moneybook.ui.layouts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.navigation.NavGraph
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme

@Composable
fun AppLayout() {
    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        BottomBarLayout(navController) {
            NavGraph(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppLayoutPreview() {
    MoneyBookTheme {
        AppLayout()
    }
}