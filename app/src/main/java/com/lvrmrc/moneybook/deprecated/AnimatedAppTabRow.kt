package com.lvrmrc.moneybook.deprecated

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.lvrmrc.moneybook.domain.model.transactionsTabs
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimatedAppTabRow(pagerState: PagerState, showBottomBar: Boolean) {
    AnimatedVisibility(visible = showBottomBar,
        enter = expandVertically(initialHeight = { 0 }, expandFrom = Alignment.Bottom),
        exit = shrinkVertically(targetHeight = { it }, shrinkTowards = Alignment.Top),
        content = {
            AppTabRow(pagerState)
        })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppTabRow(pagerState: PagerState, appState: AppState = AppState.getInstance()) {
    TabRow(
        selectedTabIndex = pagerState.currentPage, containerColor = colorScheme.primary, contentColor = colorScheme.onPrimary
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
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun AppTabRowPreview() {
    val pagerState = rememberPagerState(0, initialPageOffsetFraction = 0f, pageCount = { transactionsTabs.size })

    MoneyBookTheme {
        AnimatedAppTabRow(pagerState, true)
    }
}




