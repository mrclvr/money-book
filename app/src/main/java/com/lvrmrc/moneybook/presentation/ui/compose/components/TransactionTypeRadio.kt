package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.domain.model.TransactionType

@Composable
fun TransactionTypeRadio(
    selected: TransactionType? = null, onSelected: (TransactionType) -> Unit = {}
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = selected == TransactionType.EXPENSE, onClick = { onSelected(TransactionType.EXPENSE) })
        Text(stringResource(R.string.expense))

        RadioButton(selected = selected == TransactionType.INCOME, onClick = { onSelected(TransactionType.INCOME) })
        Text(stringResource(R.string.income))
    }
}

@Composable
@Preview(showBackground = true)
fun TransactionTypeRadioPreview() {
    TransactionTypeRadio()
}