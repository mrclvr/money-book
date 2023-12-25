package com.lvrmrc.moneybook.presentation.ui.compose.components.layout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme


@Composable
fun AppLayout(
    content: @Composable () -> Unit = {}
) {
    MoneyBookTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@Composable
fun NavProvider(content: @Composable () -> Unit = {}) {
    CompositionLocalProvider(LocalNavController provides rememberNavController()) { content() }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppLayoutPreview() {
    AppLayout() {}
}