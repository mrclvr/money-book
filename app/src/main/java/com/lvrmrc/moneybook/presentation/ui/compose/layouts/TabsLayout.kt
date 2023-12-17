package com.lvrmrc.moneybook.presentation.ui.compose.layouts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.presentation.ui.compose.components.tabs.customTabIndicatorOffset
import com.lvrmrc.moneybook.presentation.ui.compose.components.tabs.transactionsTabs
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.AppDrawer
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme
import kotlinx.coroutines.launch

val LocalFabVisible = compositionLocalOf {
    true
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TabsLayout(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    onNavClick: (TransactionType) -> Unit = {},
    index: Int = 0,
    content: @Composable () -> Unit = {},
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())


    val appScope = rememberCoroutineScope()
    val bottomBarVisible = rememberSaveable { (mutableStateOf(true)) }
    val fabVisible = rememberSaveable { (mutableStateOf(true)) }
//    val pagerState = rememberPagerState(initialPage, initialPageOffsetFraction = 0f, pageCount = { transactionsTabs.size })
    val snackBarHostState = remember { SnackbarHostState() }
    val currentPage = remember { mutableIntStateOf(index) }

    AppDrawer(navController, drawerState) {
        CompositionLocalProvider(LocalFabVisible provides fabVisible.value) {
            Scaffold(
//                containerColor = primary50,
                modifier = Modifier.fillMaxSize(),
                snackbarHost = {
                    SnackbarHost(hostState = snackBarHostState)
                },
                floatingActionButtonPosition = FabPosition.Center,
                floatingActionButton = {
                    AnimatedAppFAB(navController, LocalFabVisible.current)
                },
                bottomBar = {
                    TabRow(
                        modifier = Modifier.clip(
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
                        },
                    ) {

                        transactionsTabs.forEachIndexed { index, tab ->
                            Tab(
                                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
                                selected = index == currentPage.intValue,
                                text = { Text(text = tab.title) },
//                                icon = { Icon(tab.icon, tab.title, Modifier.size(30.dp)) },
                                onClick = {
                                    currentPage.intValue = index
                                    onNavClick(tab.type)
//                                    appState.setTransType(tab.type)
                                },
                            )
                        }
                    }
                },
                topBar = {
                    TopAppBar(
                        modifier = Modifier
                            .width(58.dp)
                            .clip(RoundedCornerShape(0, 0, 50, 0)),
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = colorScheme.onPrimary,
                        ),
                        title = {
                            Text(
                                ""
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { appScope.launch { drawerState.open() } }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu, contentDescription = "Open drawer", tint = colorScheme.primary
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                },

                ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = it)
                ) {

//                    HorizontalPager(
//                        modifier = Modifier.fillMaxSize(), state = pagerState
//                    ) {
//                    }
                    content()

                    navController.addOnDestinationChangedListener { _, destination, _ ->
                        fabVisible.value = Screen.hasFAB(destination.route)
                        bottomBarVisible.value = Screen.hasNavbar(destination.route)
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TabsLayoutPreview() {
    MoneyBookTheme {
        TabsLayout() {}
    }
}