package com.lvrmrc.moneybook.presentation.ui.compose.layouts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.presentation.ui.compose.components.tabs.transactionsTabs
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsLayout(
    navController: NavHostController = rememberNavController(),
    initialPage: Int = 0,
    onLayoutNavClick: (TransactionType) -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val bottomBarVisible = rememberSaveable { (mutableStateOf(true)) }
    val pagerState = rememberPagerState(initialPage, initialPageOffsetFraction = 0f, pageCount = { transactionsTabs.size })
//    val transType = remember { mutableStateOf(TransactionType.EXPENSE) }
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute: NavDestination? = navBackStackEntry?.destination

    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButtonPosition = FabPosition.Center, floatingActionButton = {
        AnimatedAppFAB(navController, LocalFabVisible.current)
    }, bottomBar = {
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
                            onLayoutNavClick(tab.type)
                        }
                    },
                )
            }
        }
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
                .background(MaterialTheme.colorScheme.background)
        ) {

            HorizontalPager(
                state = pagerState
            ) {
//                when (pagerState.currentPage) {
//                    0 -> content()
//                    1 -> content()
//                }
                content()
            }
        }
    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        bottomBarVisible.value = Screen.hasNavbar(destination.route)
    }
}


@Preview
@Composable
fun TabsLayoutPreview() {
    MoneyBookTheme {
        AppLayout {
            TabsLayout()
        }
    }
}