package com.lvrmrc.moneybook.presentation.ui.compose.layouts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.data.AppState
import com.lvrmrc.moneybook.presentation.ui.compose.components.tabs.transactionsTabs
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

val LocalFabVisible = compositionLocalOf {
    true
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TabsLayout(
    appState: AppState = AppState.getInstance(),
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    initialPage: Int = 0,
    content: @Composable () -> Unit = {},
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    val appScope = rememberCoroutineScope()
    val bottomBarVisible = rememberSaveable { (mutableStateOf(true)) }
    val fabVisible = rememberSaveable { (mutableStateOf(true)) }
    val pagerState = rememberPagerState(initialPage, initialPageOffsetFraction = 0f, pageCount = { transactionsTabs.size })
    val snackBarHostState = remember { SnackbarHostState() }

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet {
            Text("Money Book", modifier = Modifier.padding(16.dp))
            Divider()
            NavigationDrawerItem(label = { Text(text = "Drawer Item") }, selected = false, onClick = { /*TODO*/ })
        }
    }) {

        CompositionLocalProvider(LocalFabVisible provides fabVisible.value) {
            Scaffold(
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
                        selectedTabIndex = pagerState.currentPage,
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        val tabRowScope: CoroutineScope = rememberCoroutineScope()

                        transactionsTabs.forEachIndexed { index, tab ->
                            Tab(
                                selected = index == pagerState.currentPage,
                                text = { Text(text = tab.title) },
                                icon = { Icon(tab.icon, tab.title) },
                                onClick = {
                                    tabRowScope.launch {
                                        pagerState.scrollToPage(index)
                                    }

                                    if (tab.type != null) {
                                        appState.setTransType(tab.type)
                                    }
                                },
                            )
                        }
                    }
                },
                topBar = {
                    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ), title = {
                        Text(
                            ""
                        )
                    }, navigationIcon = {
                        IconButton(onClick = { appScope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Filled.Menu, contentDescription = "Localized description"
                            )
                        }
                    }, scrollBehavior = scrollBehavior
                    )
                },

                ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = it)
                        .background(MaterialTheme.colorScheme.background)
                ) {

                    HorizontalPager(
                        modifier = Modifier.fillMaxSize(), state = pagerState
                    ) {
                        content()
                    }

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