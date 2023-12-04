package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.data.mockCategories
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.presentation.ui.compose.components.DialogDatePicker
import com.lvrmrc.moneybook.presentation.ui.compose.components.LabeledSection
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.TabsLayout
import com.lvrmrc.moneybook.presentation.ui.theme.MoneyBookTheme
import com.lvrmrc.moneybook.presentation.viewmodel.TransactionViewModel
import java.time.LocalDateTime

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
    amount: Double,
    notes: String,
    date: LocalDateTime,
    setAmount: (Double) -> Unit = {},
    setNotes: (String) -> Unit = {},
    setDate: (Long) -> Unit = {},
    onAddTransaction: () -> Unit = {},
    onLayoutNavClick: (TransactionType) -> Unit = {},
) {

//    var amount by remember { mutableDoubleStateOf(0.0) }
//    var notes by remember { mutableStateOf("") }
//    var date by remember { mutableStateOf("") }

//    FABLayout(
//        onFabAction = {
//            onAddTransaction()
//        }, fabEnabled = notes.isNotBlank()
//    ) {
//        Column(
//            modifier = Modifier.fillMaxSize()
//        )
    TabsLayout(onLayoutNavClick = { onLayoutNavClick(it) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
                .background(Color.Transparent),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        )
        {
            LabeledSection(sectionTitle = "Amount") {
                TextField(modifier = Modifier.fillMaxWidth(1f),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    value = amount.toString(),
                    onValueChange = { value ->
                        when (value.toDoubleOrNull()) {
                            null -> {}
                            else -> setAmount(value.filter { it.isDigit() }.toDouble())
                        }

                    },
                    prefix = { Text("EUR") })
            }
            LabeledSection(sectionTitle = "Notes") {
                TextField(modifier = Modifier.fillMaxWidth(1f), singleLine = true, value = notes, onValueChange = { value ->
                    setNotes(value)
                })
            }
            LabeledSection(sectionTitle = "Category", horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                mockCategories.forEach { cat ->
                    IconButton(modifier = Modifier.size(56.dp), colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = cat.color,
                        contentColor = if (cat.lightText) colorScheme.background else colorScheme.onBackground
                    ), onClick = { /* do something */ }) {
                        Icon(
                            cat.icon, contentDescription = "${cat.label} category"
                        )
                    }
                }
            }
            LabeledSection(
                fillHeight = true, sectionTitle = "Date", horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DialogDatePicker {
                    setDate(it)
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun TransactionScreenPreview(
) {
    MoneyBookTheme {
        TransactionScreen(amount = 10.0, notes = "Notes", date = LocalDateTime.now())

    }
}