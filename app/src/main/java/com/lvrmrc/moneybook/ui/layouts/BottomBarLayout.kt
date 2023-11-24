package com.lvrmrc.moneybook.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.data.entity.Transaction
import com.lvrmrc.moneybook.ui.components.AppNavBar
import com.lvrmrc.moneybook.ui.screens.Screen
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme
import com.lvrmrc.moneybook.viewmodels.ExpenseViewModel
import kotlinx.coroutines.launch


@Composable
fun BottomBarLayout(navController: NavHostController, viewModel: ExpenseViewModel = hiltViewModel(), content: @Composable() () -> Unit) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = { AppNavBar(navController = navController) },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.offset(0.dp, 50.dp), shape = CircleShape, onClick = {
                    coroutineScope.launch {
                        viewModel.addExpense(
                            Transaction(
                                amount = 5.55, title = "Pippo", type = "Expense"
                            )
                        )

                    }
//                    navController.navigate(Screen.Transaction.route) {
//
//                        navController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) {
//                                saveState = true
//                            }
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
                }, elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Screen.Transaction.icon, Screen.Transaction.label)
            }
        }) {
        Box(
            modifier = Modifier
                .padding(paddingValues = it)
                .background(colorScheme.primary),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        25.dp, 50.dp
                    ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavBarLayoutPreview() {
    MoneyBookTheme {
        BottomBarLayout(rememberNavController(), content = {})
    }
}