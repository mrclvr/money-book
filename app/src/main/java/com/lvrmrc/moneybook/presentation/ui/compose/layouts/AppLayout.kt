package com.lvrmrc.moneybook.presentation.ui.compose.layouts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme


@Composable
fun AppLayout(
    navController: NavHostController = rememberNavController(), content: @Composable () -> Unit = {}
) {

    MoneyBookTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppLayoutPreview() {
    AppLayout() {}
}