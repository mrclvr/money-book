package com.lvrmrc.moneybook.presentation.ui.compose.components.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.navigateDefault
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen

@Composable
fun AnimatedAppFAB(modifier: Modifier = Modifier, showFAB: Boolean) {
    val navController = LocalNavController.current

    AnimatedVisibility(visible = showFAB, enter = scaleIn(), exit = scaleOut(), content = {
        AppFAB(modifier = modifier, onClick = {
            navController.navigateDefault(Screen.Transaction.route)
        })
    })
}

@Composable
fun AppFAB(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {

    FloatingActionButton(
        modifier = modifier,
        containerColor = colorScheme.secondary,
        contentColor = colorScheme.onSecondary,
        shape = CircleShape,
        onClick = {
            onClick()
        },
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
    ) {
        Icon(Icons.Filled.Add, Screen.Transaction.label)
    }
}

@Preview(showBackground = true)
@Composable
fun AppFABPreview() {
    NavProvider {
        AnimatedAppFAB(showFAB = true)
    }
}




