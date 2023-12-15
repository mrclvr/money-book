package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.data.mockCategories
import com.lvrmrc.moneybook.data.mockTransactions
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme

@Composable
fun TransactionsListItem(transaction: Transaction, category: Category, onClick: () -> Unit = {}) {
    Box(modifier = Modifier.padding(10.dp)) {

        LabeledSection(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .background(colorScheme.primaryContainer)
                .clickable { onClick() },
            sectionTitle = transaction.date.toString()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    IconButton(modifier = Modifier.size(32.dp), colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = category.color,
                        contentColor = if (category.lightText) colorScheme.background else colorScheme.onBackground
                    ), onClick = { }) {
                        Icon(
                            category.icon, contentDescription = "${category.label} category"
                        )
                    }
                    Text(text = transaction.notes)
                }
                Text(
                    text = "€ ${
                        transaction.amount
                    }"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionsListItemPreview() {
    MoneyBookTheme {
        TransactionsListItem(mockTransactions[0], mockCategories[0])
    }
}