package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvrmrc.moneybook.domain.model.TransactionPeriod
import com.lvrmrc.moneybook.utils.localFormat
import com.lvrmrc.moneybook.utils.localMonth
import java.time.LocalDateTime

enum class DateDirection {
    MINUS, PLUS
}

@Composable
fun DateSelector(period: TransactionPeriod, date: LocalDateTime, onDateBack: () -> Unit = {}, onDateForward: () -> Unit = {}) {
    val text = when (period) {
        TransactionPeriod.DAY -> date.localFormat()
        TransactionPeriod.MONTH -> "${date.localMonth()} ${date.year}"
        TransactionPeriod.YEAR -> date.year

        else -> "Unsupported"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(36.dp)
                .clickable { onDateBack() },
            imageVector = Icons.Rounded.ChevronLeft,
            contentDescription = "Back",
            tint = colorScheme.onBackground
        )
        Text(text = "$text", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = colorScheme.onBackground)
        Icon(
            modifier = Modifier
                .size(36.dp)
                .clickable { onDateForward() },
            imageVector = Icons.Rounded.ChevronRight,
            contentDescription = "Forward",
            tint = colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DateSelectorPreview() {
    DateSelector(TransactionPeriod.MONTH, LocalDateTime.now())
}