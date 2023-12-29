package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.domain.model.TransactionPeriod

@Composable
fun TransactionPeriodRadio(
    selected: TransactionPeriod? = null, onSelected: (TransactionPeriod) -> Unit = {}
) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        RadioButton(
            modifier = Modifier.size(20.dp),
            selected = selected == TransactionPeriod.DAY,
            onClick = { onSelected(TransactionPeriod.DAY) })
        Text(stringResource(R.string.day))

        RadioButton(
            modifier = Modifier.size(20.dp),
            selected = selected == TransactionPeriod.MONTH,
            onClick = { onSelected(TransactionPeriod.MONTH) })
        Text(stringResource(R.string.month))

        RadioButton(
            modifier = Modifier.size(20.dp),
            selected = selected == TransactionPeriod.YEAR,
            onClick = { onSelected(TransactionPeriod.YEAR) })
        Text(stringResource(R.string.year))
    }
}

@Composable
@Preview(showBackground = true)
fun TransactionPeriodRadioPreview() {
    TransactionPeriodRadio()
}