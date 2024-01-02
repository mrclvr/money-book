package com.lvrmrc.moneybook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.AppLayout
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No LocalNavController provided")
}

@AndroidEntryPoint()
class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController: NavHostController = rememberNavController()

            CompositionLocalProvider(LocalNavController provides navController) {
                AppLayout { NavGraph() }
            }
        }
    }
}

