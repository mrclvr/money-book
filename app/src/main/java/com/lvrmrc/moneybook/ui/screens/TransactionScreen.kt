package com.lvrmrc.moneybook.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.ui.components.DialogDatePicker
import com.lvrmrc.moneybook.ui.components.LabeledSection
import com.lvrmrc.moneybook.ui.layouts.TopBarLayout
import com.lvrmrc.moneybook.ui.theme.MoneyBookTheme
import com.lvrmrc.moneybook.viewmodels.TransactionViewModel

@Composable
fun TransactionScreen(navController: NavHostController = rememberNavController(), vm: TransactionViewModel = hiltViewModel()) {
    TransactionScreen(
        onAddTransaction = {
            vm.addTransaction()
            navController.popBackStack()
        },
        amount = vm.amount.value,
        setAmount = vm::setAmount,
        notes = vm.notes.value,
        setNotes = vm::setNotes,
        date = vm.date.value,
        setDate = vm::setDate
    )
}

@Composable
private fun TransactionScreen(
    onAddTransaction: () -> Unit,
    amount: Double,
    setAmount: (Double) -> Unit,
    notes: String,
    setNotes: (String) -> Unit,
    date: String,
    setDate: (String) -> Unit
) {

//    var amount by remember { mutableDoubleStateOf(0.0) }
//    var notes by remember { mutableStateOf("") }
//    var date by remember { mutableStateOf("") }

    TopBarLayout(
        onFabAction = {
            onAddTransaction()
        }, fabEnabled = date.isNotBlank()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp), verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            LabeledSection("Amount") {
                TextField(modifier = Modifier.fillMaxWidth(1 / 2f), value = amount.toString(), onValueChange = { value ->
                    when (value.toDoubleOrNull()) {
                        null -> {}
                        else -> setAmount(value.filter { it.isDigit() }.toDouble())
                    }

                }, prefix = { Text("EUR") })
            }
            LabeledSection("Notes") {
                TextField(modifier = Modifier.fillMaxWidth(1 / 2f), singleLine = true, value = notes, onValueChange = { value ->
                    setNotes(value)
                })
            }
            LabeledSection("Category", Arrangement.SpaceBetween) {
                for (i: Int in 1..5) {
                    IconButton(modifier = Modifier.size(48.dp),
                        colors = IconButtonDefaults.filledTonalIconButtonColors(),
                        onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.Cake,
                            contentDescription = "icon",
                            tint = colorScheme.primary,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(5.dp)
                        )
                    }
                }
            }
            LabeledSection("Date", Arrangement.SpaceBetween) {
                DialogDatePicker(onDateSelected = {
                    setDate(it)
                })
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun TransactionScreenPreview(
) {
    MoneyBookTheme {
        TransactionScreen()
    }
}