package com.lvrmrc.moneybook.presentation.ui.compose.layouts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme

@Composable
fun AnimatedAppFAB(showFAB: Boolean) {
    val navController = LocalNavController.current

    AnimatedVisibility(visible = showFAB, enter = scaleIn(), exit = scaleOut(), content = {
        AppFAB(onClick = {
            navController.navigate(Screen.Transaction.route) {

                navController.graph.route?.let { route ->
                    popUpTo(route) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        })
    })
}

@Composable
fun AppFAB(onClick: () -> Unit = {}) {

    FloatingActionButton(
//        modifier = Modifier.offset(y = 8.dp),
        containerColor = colorScheme.secondaryContainer, contentColor = colorScheme.onSecondaryContainer, shape = CircleShape, onClick = {
            onClick()
        }, elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
    ) {
        Icon(Screen.Transaction.icon, Screen.Transaction.label)
    }
}

@Preview(showBackground = true)
@Composable
fun AppFABPreview() {
    MoneyBookTheme {
        AnimatedAppFAB(true)
    }
}




