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
import com.lvrmrc.moneybook.domain.model.TransactionType

@Composable
fun TransactionTypeRadio(
    selected: TransactionType? = null, onSelected: (TransactionType) -> Unit = {}
) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        RadioButton(modifier = Modifier.size(20.dp),
            selected = selected == TransactionType.EXPENSE,
            onClick = { onSelected(TransactionType.EXPENSE) })
        Text(stringResource(R.string.expense))

        RadioButton(modifier = Modifier.size(20.dp),
            selected = selected == TransactionType.INCOME,
            onClick = { onSelected(TransactionType.INCOME) })
        Text(stringResource(R.string.income))
    }
}

@Composable
@Preview(showBackground = true)
fun TransactionTypeRadioPreview() {
    TransactionTypeRadio()
}