package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.data.expenseCategories
import com.lvrmrc.moneybook.data.mockTransactions
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.TransactionWithCategory
import com.lvrmrc.moneybook.domain.model.darken
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.navigateDefault
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen
import com.lvrmrc.moneybook.utils.localFormat
import com.lvrmrc.moneybook.utils.removeDecimal
import java.util.UUID

@Composable
fun TransactionsListItem(
    transactions: List<TransactionWithCategory>,
    category: Category? = null,
    showCategoryIcon: Boolean = false,
    outlined: Boolean = false,
    onDelete: (UUID) -> Unit = {}
) {
    val titleColor = if (isSystemInDarkTheme() || category == null) colorScheme.onBackground else category.color.darken(0.3f)
    val sectionTitle = transactions[0].date.localFormat() ?: stringResource(R.string.undefined_date)

    LabeledSection(
        sectionTitle = sectionTitle, titleColor = titleColor
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            val navController = LocalNavController.current

            transactions.forEach() {
                val outlinedModifier = Modifier
                    .border(1.dp, it.category.color, RoundedCornerShape(20))
                    .padding(8.dp)

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigateDefault("${Screen.Transaction.route}?transactionId=${it.id}") }
                    .then(if (outlined) outlinedModifier else Modifier),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        if (showCategoryIcon) {
                            val iconColor = if (it.category.lightText) colorScheme.background else colorScheme.onBackground

                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(it.category.color, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    it.category.icon, it.category.label, Modifier.size(20.dp), iconColor
                                )
                            }
                        }
                        Column {
                            if (showCategoryIcon) {
                                Text(it.category.label, fontSize = 14.sp, color = it.category.color)
                            }
                            Text(it.notes, fontSize = 18.sp)
                        }

                    }
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                        Text(
                            text = "${
                                it.amount.removeDecimal()
                            }€", fontSize = 20.sp
                        )

                        var showDialog by remember { mutableStateOf(false) }

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(colorScheme.background, CircleShape)
                                .clickable {
                                    showDialog = true
                                }, contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Outlined.Delete, stringResource(R.string.delete_transaction), tint = colorScheme.error
                            )

                            if (showDialog) {
                                CustomAlertDialog(title = stringResource(R.string.confirm_deletion),
                                    text = stringResource(R.string.transaction_delete_msg, it.notes),
                                    onDismissRequest = { showDialog = false },
                                    onConfirmation = {
                                        onDelete(it.id)
                                        showDialog = false
                                    })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TransactionsListItemPreview() {
    NavProvider {
        Column(Modifier.padding(16.dp)) {
            TransactionsListItem(mockTransactions.map { it.toTransactionWithCategory(expenseCategories[0]) }, null, true)

        }
    }
}