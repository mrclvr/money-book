package com.lvrmrc.moneybook.ui.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarLayout(
    navController: NavHostController, title: String = "", content: @Composable() () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ), title = {
                Text(title)
            },

                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack, contentDescription = "ArrowBack"
                        )
                    }
                })
        },
//        floatingActionButtonPosition = FabPosition.Center, floatingActionButton = {
//        FloatingActionButton(
//            shape = CircleShape, onClick = {
//                //ricarica conto
//            }, elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
//        ) {
//            Icon(Screen.Transaction.icon, Screen.Transaction.label)
//        }
//    }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun TopBarLayoutPreview() {
    MoneyBookTheme {
        TopBarLayout(rememberNavController(), "Title")
    }
}