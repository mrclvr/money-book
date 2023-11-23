package com.lvrmrc.moneybook.ui.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.navigation.NavGraph
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme

@Composable
fun AppLayout() {
    val snackBarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            },
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it),
            ) {
                NavGraph(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoneyBookTheme {
        AppLayout()
    }
}