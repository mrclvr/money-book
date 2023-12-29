package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.data.periodTabs
import com.lvrmrc.moneybook.domain.model.TransPeriodTabItem
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.domain.model.periodIntMap
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.AnimatedAppFAB
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsCard(
    tabs: List<TransPeriodTabItem>, period: TransactionPeriod,
//    initialPage: Int = 0,
    onSetPeriod: (TransactionPeriod) -> Unit = {}, cardContent: @Composable () -> Unit = {}
) {

    val pagerState = rememberPagerState(initialPage = periodIntMap[period] ?: 0, initialPageOffsetFraction = 0f, pageCount = { tabs.size })

    Card(
        modifier = Modifier
            .defaultMinSize(300.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(6),
        colors = CardDefaults.cardColors(containerColor = colorScheme.surface, contentColor = colorScheme.onSurface),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )

    ) {

        val cardScope: CoroutineScope = rememberCoroutineScope()

        Column {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(selected = index == pagerState.currentPage,
                        text = { Text(text = stringResource(tab.title)) },
                        icon = { Icon(tab.icon, "") },
                        onClick = {
                            cardScope.launch {
                                pagerState.animateScrollToPage(index)
//                                pagerState.scrollToPage(index)
                            }
                            onSetPeriod(tab.period)
                        })
                }
            }
            HorizontalPager(
                state = pagerState, modifier = Modifier
                    .fillMaxSize()
                    .background(colorScheme.background)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    when (cardContent) {
                        {} -> tabs[pagerState.currentPage].content()
                        else -> cardContent()
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomEnd)
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        AnimatedAppFAB(showFAB = true)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun TabsCardPreview() {
    NavProvider {
        TabsCard(periodTabs, TransactionPeriod.MONTH)
    }
}