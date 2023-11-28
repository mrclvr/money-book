package com.lvrmrc.moneybook.ui.layouts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.screens.Screen
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme

@Composable
fun AnimatedAppFAB(navController: NavHostController, showFAB: Boolean) {
    AnimatedVisibility(visible = showFAB, enter = scaleIn(), exit = scaleOut(), content = {
        AppFAB(navController)
    })
}

@Composable
fun AppFAB(navController: NavHostController) {
    FloatingActionButton(
        modifier = Modifier.offset(y = (-24).dp), shape = CircleShape, onClick = {
            navController.navigate(Screen.Transaction.route) {

                navController.graph.route?.let { route ->
                    popUpTo(route) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        }, elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
    ) {
        Icon(Screen.Transaction.icon, Screen.Transaction.label)
    }
}

@Preview(showBackground = true)
@Composable
fun AppFABPreview() {
    MoneyBookTheme {
        AnimatedAppFAB(navController = rememberNavController(), true)
    }
}




