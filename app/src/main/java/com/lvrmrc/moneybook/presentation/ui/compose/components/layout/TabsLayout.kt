package com.lvrmrc.moneybook.presentation.ui.compose.components.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.data.transactionsTabs
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.domain.model.customTabIndicatorOffset
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.AppDrawer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabsLayout(
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    index: Int = 0,
    onNavClick: (TransactionType) -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    val appScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val snackBarHostState = remember { SnackbarHostState() }
    val currentPage = remember { mutableIntStateOf(index) }

    AppDrawer(drawerState) {
        Scaffold(modifier = Modifier.fillMaxSize(), snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }, bottomBar = {
            TabRow(modifier = Modifier.clip(
                RoundedCornerShape(100, 100, 0, 0)
            ),
                selectedTabIndex = currentPage.intValue,
                containerColor = colorScheme.primary,
                contentColor = colorScheme.onPrimary,
                indicator = @Composable { tabPositions ->
                    if (currentPage.intValue < tabPositions.size) {
                        TabRowDefaults.Indicator(
                            modifier = Modifier
                                .customTabIndicatorOffset(tabPositions[currentPage.intValue], 50.dp)
                                .padding(0.dp, 12.dp)
                                .clip(RoundedCornerShape(8.dp)), color = colorScheme.onPrimary
                        )
                    }
                }) {
                transactionsTabs.forEachIndexed { index, tab ->
                    Tab(
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
                        selected = true,
                        text = { Text(text = stringResource(tab.title)) },
                        onClick = {
                            currentPage.intValue = index
                            onNavClick(tab.type)
                        },
                    )
                }
            }
        }, topBar = {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
            ), title = {
                Text("")
            }, navigationIcon = {
                IconButton(onClick = { appScope.launch { drawerState.open() } }) {
                    Icon(
                        imageVector = Icons.Filled.Menu, contentDescription = "Open drawer", tint = colorScheme.primary
                    )
                }
            }, scrollBehavior = scrollBehavior
            )
        }

        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it)
            ) {
                content()
//                    navController.addOnDestinationChangedListener { _, destination, _ ->
//                        fabVisible.value = Screen.hasFAB(destination.route)
//                        bottomBarVisible.value = Screen.hasNavbar(destination.route)
//                    }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TabsLayoutPreview() {
    NavProvider {
        TabsLayout()
    }
}