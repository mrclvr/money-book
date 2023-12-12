package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.data.mockCatTransactions
import com.lvrmrc.moneybook.domain.model.CategoryWithTransactions
import com.lvrmrc.moneybook.domain.model.Transaction
import com.lvrmrc.moneybook.presentation.ui.compose.components.LabeledSection
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.AppLayout
import com.lvrmrc.moneybook.presentation.viewmodel.ExpenseViewModel

@Composable
fun TransactionsDetailsScreen(
    vm: ExpenseViewModel = hiltViewModel()
) {
    TransactionsDetailsScreen(category = vm.selectedCategory.value)
}

@Composable
private fun TransactionsDetailsScreen(
    category: CategoryWithTransactions?
) {

    if (category != null) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()

        ) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = category.label,
                    textAlign = TextAlign.Center,
                    color = colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }

            items(category.transactions.size) { idx ->
                Box(modifier = Modifier.padding(10.dp)) {

                    val transaction: Transaction = category.transactions[idx]

                    LabeledSection(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(16.dp))
                            .background(colorScheme.primaryContainer),
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

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TransactionsDetailsScreenPreview(
) {
    AppLayout {
        TransactionsDetailsScreen(mockCatTransactions[0])
    }
}