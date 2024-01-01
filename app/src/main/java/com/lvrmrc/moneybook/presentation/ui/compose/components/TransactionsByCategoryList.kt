package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.AppLayout
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme
import com.lvrmrc.moneybook.utils.removeDecimal
import com.lvrmrc.moneybook.utils.toFloatPercentage
import kotlin.math.roundToInt

/**
 * List of expenses grouped by category
 */
@Composable
fun TransactionsByCategoryList(
    modifier: Modifier = Modifier, catTransactions: List<CategoryWithTransactions>, onSetCategory: (CategoryWithTransactions) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
//            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),

        ) {

        val periodTotal = catTransactions.sumOf { it.total }

        items(catTransactions) { cat ->
            val textColor = if (cat.lightText) colorScheme.background else colorScheme.onBackground
            val percentage = cat.total.toFloatPercentage(periodTotal).roundToInt()
            Card(
                shape = RoundedCornerShape(8.dp), elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(colorScheme.background)
                    .clickable { onSetCategory(cat) }
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Row(
                        modifier = Modifier.weight(0.6f),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .aspectRatio(1f)
                                .clip(shape = CircleShape)
                                .background(cat.color),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(20.dp), imageVector = cat.icon, contentDescription = cat.label, tint = textColor
                            )
                        }
                        Text(text = cat.label, color = colorScheme.onPrimaryContainer)
                    }
                    Row(
                        modifier = Modifier.weight(0.4f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "${percentage}%", color = colorScheme.onPrimaryContainer)
                        Text(text = "${cat.total.removeDecimal()} €", color = colorScheme.onPrimaryContainer)
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionsByCategoryPreview() {
    AppLayout {
        TransactionsByCategoryList(catTransactions = mockCatTransactions)
    }
}