package com.lvrmrc.moneybook.presentation.ui.compose.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FABLayout(
    fabText: String = "Text", fabEnabled: Boolean = false, onFabAction: () -> Unit = {}, content: @Composable() () -> Unit = {}
) {

    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButtonPosition = FabPosition.Center, floatingActionButton = {
        Button(
            onClick = {
                onFabAction()
            }, modifier = Modifier.defaultMinSize(minWidth = 56.dp, minHeight = 56.dp), enabled = fabEnabled, shape = CircleShape

        ) {
            Text(text = fabText)
//            Icon(Icons.Filled.ArrowForward, contentDescription = "Add transaction")
        }
    }

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues = it),
        ) {
            content()
        }
    }
}


@Preview
@Composable
fun FABLayoutPreview() {
    AppLayout {
        FABLayout()
    }
}