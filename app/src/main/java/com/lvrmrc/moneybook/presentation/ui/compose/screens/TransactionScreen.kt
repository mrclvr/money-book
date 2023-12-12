package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.data.mockCategories
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.presentation.ui.compose.components.CategoriesGrid
import com.lvrmrc.moneybook.presentation.ui.compose.components.DialogDatePicker
import com.lvrmrc.moneybook.presentation.ui.compose.components.LabeledSection
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.AppLayout
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.FABLayout
import com.lvrmrc.moneybook.presentation.viewmodel.TransactionViewModel
import com.lvrmrc.moneybook.utils.NumberUtils
import java.time.LocalDateTime

@Composable
fun TransactionScreen(
    navController: NavHostController = rememberNavController(), vm: TransactionViewModel = hiltViewModel()
) {
    TransactionScreen(
        onAddTransaction = {
            vm.addTransaction()
            navController.popBackStack()
        },
        amount = vm.amount.value,
        setAmount = vm::setAmount,
        notes = vm.notes.value,
        setNotes = vm::setNotes,
        category = vm.category.value,
        setCategory = vm::setCategory,
        date = vm.date.value,
        setDate = vm::setDate
    )
}

@Composable
private fun TransactionScreen(
    amount: String,
    notes: String,
    category: Category? = null,
    date: LocalDateTime = LocalDateTime.now(),
    setAmount: (String) -> Unit = {},
    setNotes: (String) -> Unit = {},
    setDate: (Long) -> Unit = {},
    setCategory: (Category) -> Unit = {},
    onAddTransaction: () -> Unit = {}
) {

    FABLayout(onFabAction = onAddTransaction, fabEnabled = notes.isNotBlank()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
//            verticalArrangement = Arrangement.spacedBy(25.dp),
//            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            LabeledSection(horizontalArrangement = Arrangement.Center) {
                TextField(modifier = Modifier.fillMaxWidth(0.5f),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    value = amount,
                    onValueChange = {
                        setAmount(NumberUtils.clean(it))
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    prefix = { Text("EUR") })
            }
            LabeledSection(
//                modifier = Modifier.weight(1f, true),
                sectionTitle = "Category",
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                CategoriesGrid(mockCategories, selected = category, onSelected = { setCategory(it) })

            }
            LabeledSection(sectionTitle = "Notes") {
                TextField(modifier = Modifier.fillMaxWidth(1f), singleLine = true, value = notes, onValueChange = { value ->
                    setNotes(value)
                })

            }
            LabeledSection(
                sectionTitle = "Date", horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DialogDatePicker {
                    setDate(it)
                }

            }
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TransactionScreenPreview(
) {
    AppLayout {
        TransactionScreen(amount = "10.0", notes = "Notes")
    }
}