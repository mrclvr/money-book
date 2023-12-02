package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.BottomBarLayout
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme
import com.lvrmrc.moneybook.presentation.viewmodel.IncomeViewModel

@Composable
fun IncomeScreen(
    vm: IncomeViewModel = hiltViewModel()

) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(vm.appState.loading.toString())
        Button(onClick = { vm.appState.setLoading(!vm.appState.loading) }) {
            Text(
                text = "INCOME"
//            modifier = Modifier.clickable { navController.navigate(route = Screen.Home.route)
//    }
            )
        }
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