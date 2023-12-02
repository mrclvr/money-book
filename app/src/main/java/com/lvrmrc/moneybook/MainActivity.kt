package com.lvrmrc.moneybook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.AppLayout
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.NavGraph
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint()
class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoneyBookTheme {
                val navController = rememberNavController()
                AppLayout(navController) {
                    NavGraph(navController)
                }
            }
        }
    }
}