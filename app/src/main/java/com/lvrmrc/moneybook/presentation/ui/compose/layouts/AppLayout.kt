package com.lvrmrc.moneybook.presentation.ui.compose.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.NavGraph
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme


@Composable
fun AppLayout(
//    vm: AppViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(), content: @Composable () -> Unit = {}
) {
    val fabVisible = rememberSaveable { (mutableStateOf(true)) }
    val snackBarHostState = remember { SnackbarHostState() }

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
//            floatingActionButtonPosition = FabPosition.Center,
//            floatingActionButton = {
//                AnimatedAppFAB(navController, fabVisible.value)
//            },
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                CompositionLocalProvider(LocalFabVisible provides fabVisible.value) {
                    NavGraph(navController)
                }
            }
        }

    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        fabVisible.value = Screen.hasFAB(destination.route)
    }
}

@Preview(showBackground = true)
@Composable
fun AppLayoutPreview() {
    MoneyBookTheme {
        AppLayout {}
    }
}