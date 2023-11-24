package com.lvrmrc.moneybook.ui.components.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme
import com.lvrmrc.moneybook.viewmodels.ExpenseViewModel

@Composable
fun TimeTabsCard(expenseViewModel: ExpenseViewModel) {
    TabsCard(transactionPeriodTabs(expenseViewModel), expenseViewModel.periodTabIndex.value)
}

@Preview
@Composable
private fun TimeTabsCardPreview() {
    MoneyBookTheme {
        TimeTabsCard(
            hiltViewModel()
        )
    }
}