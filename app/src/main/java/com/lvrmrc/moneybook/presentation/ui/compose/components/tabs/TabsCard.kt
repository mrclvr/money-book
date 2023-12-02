package com.lvrmrc.moneybook.presentation.ui.compose.components.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.data.mockPeriodTabs
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsCard(
    tabs: List<TabItem>,
    initialPage: Int = 0,
    currentPage: Int? = null,
    onTabRowClick: (String) -> Unit = {},
    cardContent: @Composable () -> Unit = {}
) {
    val pagerState = rememberPagerState(initialPage, initialPageOffsetFraction = 0f, pageCount = { tabs.size })

    Card(
        modifier = Modifier
            .defaultMinSize(300.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )

    ) {

        val cardScope: CoroutineScope = rememberCoroutineScope()

        Column() {
            TabRow(
                selectedTabIndex = currentPage ?: pagerState.currentPage,
            ) {

                tabs.forEachIndexed { index, tab ->
                    Tab(selected = index == pagerState.currentPage,
                        text = { Text(text = tab.title) },
                        icon = { Icon(tab.icon, "") },
                        onClick = {
                            cardScope.launch {
//                                pagerState.animateScrollToPage(index)
                                pagerState.scrollToPage(index)
                            }
                            onTabRowClick(tab.title)
                        })
                }
            }
            HorizontalPager(
                state = pagerState, modifier = Modifier.fillMaxSize()
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    when (cardContent) {
                        {} -> tabs[pagerState.currentPage].content()
                        else -> cardContent()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun TabsCardPreview() {
    MoneyBookTheme {
        TabsCard(mockPeriodTabs)
    }
}