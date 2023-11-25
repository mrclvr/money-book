package com.lvrmrc.moneybook.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.data.entity.Transaction
import com.lvrmrc.moneybook.data.mockPeriodTabs
import com.lvrmrc.moneybook.data.mockTransactions
import com.lvrmrc.moneybook.ui.components.ExpensesList
import com.lvrmrc.moneybook.ui.components.tabs.TabItem
import com.lvrmrc.moneybook.ui.components.tabs.TabsCard
import com.lvrmrc.moneybook.ui.components.tabs.transactionPeriodTabs
import com.lvrmrc.moneybook.ui.layouts.BottomBarLayout
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme
import com.lvrmrc.moneybook.viewmodels.ExpenseViewModel


@Composable
fun ExpenseScreen(viewModel: ExpenseViewModel = hiltViewModel()) {

    val tabs = transactionPeriodTabs(viewModel)
    val transactions = viewModel.transactions.value
    val tabIndex = viewModel.periodTabIndex.value

    ExpenseScreen(transactions, tabs, tabIndex)
}

@Composable
private fun ExpenseScreen(transactions: List<Transaction>, tabs: List<TabItem>, tabIndex: Int = 0) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        TabsCard(tabs, tabIndex)
        ExpensesList(transactions)
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseScreenPreview() {
    MoneyBookTheme {
        BottomBarLayout {
            ExpenseScreen(mockTransactions, mockPeriodTabs)
        }
    }
}