package com.lvrmrc.moneybook.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.ui.layouts.BottomBarLayout
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme

@Composable
fun IncomeScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "INCOME",
//            modifier = Modifier.clickable { navController.navigate(route = Screen.Home.route)
//    }
        )
    }
}

@Preview
@Composable
fun IncomeScreenPreview() {
    MoneyBookTheme {
        BottomBarLayout {
            IncomeScreen()
        }
    }
}