package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.data.expenseCategories
import com.lvrmrc.moneybook.data.mockTransactions
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.domain.model.darken
import com.lvrmrc.moneybook.domain.model.lighten
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.utils.localFormat
import com.lvrmrc.moneybook.utils.removeDecimal

@Composable
fun TransactionsListItem(transaction: Transaction, category: Category, onDelete: () -> Unit = {}, onClick: () -> Unit = {}) {
    val gradient = Brush.verticalGradient(
        listOf(colorScheme.background, category.color.lighten(0.85f)), startY = 0.0f, endY = 100.0f
    )
    Card(
        shape = RoundedCornerShape(16.dp), elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        var showDialog by remember { mutableStateOf(false) }

        LabeledSection(modifier = Modifier
            .background(gradient)
            .clickable { onClick() },
            sectionTitle = transaction.date.localFormat() ?: stringResource(R.string.undefined_date),
            titleColor = category.color.darken(0.3f),
            topRightContent = {
//                IconButton(modifier = Modifier.size(32.dp), colors = IconButtonDefaults.filledTonalIconButtonColors(
//                    containerColor = colorScheme.error, contentColor = colorScheme.onError

                Icon(
                    Icons.Outlined.Delete,
                    stringResource(R.string.delete_transaction),
                    Modifier.clickable { showDialog = true },
                    colorScheme.error
                )

                if (showDialog) {
                    CustomAlertDialog(title = stringResource(R.string.confirm_deletion),
                        text = stringResource(R.string.transaction_delete_msg, transaction.notes),
                        onDismissRequest = { showDialog = false },
                        onConfirmation = {
                            onDelete()
                            showDialog = false
                        })
                }
//                }
            }) {
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
                            category.icon, category.label, Modifier.size(20.dp)
                        )
                    }
                    Text(transaction.notes, fontSize = 18.sp)
                }
                Text(
                    text = "${
                        transaction.amount.removeDecimal()
//                        NumberUtils.removeDecimal(transaction.amount)
                    }€", fontSize = 20.sp
                )
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TransactionsListItemPreview() {
    NavProvider {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            TransactionsListItem(mockTransactions[0], expenseCategories[0])
            TransactionsListItem(mockTransactions[0], expenseCategories[0])
            TransactionsListItem(mockTransactions[0], expenseCategories[0])
        }
    }
}