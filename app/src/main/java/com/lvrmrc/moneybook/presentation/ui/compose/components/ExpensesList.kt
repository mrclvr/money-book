package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.lvrmrc.moneybook.data.mockCatTransactions
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme

@Composable
fun ExpensesList(catTransactions: List<CategoryWithTransactions>, onSetCategory: (CategoryWithTransactions) -> Unit = {}) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
//            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),

        ) {

        items(catTransactions) { cat ->
            val textColor = if (cat.lightText) colorScheme.background else colorScheme.onBackground
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(cat.color)
                    .clickable { onSetCategory(cat) }
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = cat.label, color = textColor)
                Text(text = "${cat.total} €", color = textColor)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ExpensesListPreview() {
    MoneyBookTheme {
        ExpensesList(mockCatTransactions)
    }
}