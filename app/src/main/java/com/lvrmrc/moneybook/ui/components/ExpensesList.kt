package com.lvrmrc.moneybook.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.data.entity.Transaction
import com.lvrmrc.moneybook.data.mockTransactions
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme

@Composable
fun ExpensesList(transactions: List<Transaction>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
//            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),

        ) {

        items(transactions) { record ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(colorScheme.primaryContainer)
                    .padding(10.dp), content = {
                    Text(text = record.title)
                    Text(text = "${record.amount} €")
                }, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ExpensesListPreview() {
    MoneyBookTheme {
        ExpensesList(mockTransactions)
    }
}