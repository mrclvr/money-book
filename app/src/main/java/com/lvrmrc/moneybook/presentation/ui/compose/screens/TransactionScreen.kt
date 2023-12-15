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
        amount = vm.amount,
        notes = vm.notes,
        date = vm.date,
        category = vm.category,
        fabEnabled = vm.fabEnabled.value,
        onSetAmount = { vm.setAmount(it) },
        onSetNotes = { vm.setNotes(it) },
        onSetDate = { vm.setDate(it) },
        onSetCategory = { vm.setCategory(it) },
        onUpdate = {
            vm.addTransaction()
            navController.popBackStack()
        },
    )
}

@Composable
private fun TransactionScreen(
    amount: String = "",
    notes: String = "",
    date: LocalDateTime = LocalDateTime.now(),
    category: Category? = null,
    fabEnabled: Boolean = false,
    onSetAmount: (Double) -> Unit = {},
    onSetNotes: (String) -> Unit = {},
    onSetDate: (LocalDateTime) -> Unit = {},
    onSetCategory: (Category?) -> Unit = {},
    onUpdate: () -> Unit = {}
) {

    FABLayout(fabEnabled = fabEnabled, onFabAction = { onUpdate() }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            LabeledSection(horizontalArrangement = Arrangement.Center) {
                TextField(modifier = Modifier.fillMaxWidth(0.5f),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    value = amount,
                    onValueChange = {
                        NumberUtils.clean(amount).toDoubleOrNull()?.let { onSetAmount(it) }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    prefix = { Text("EUR") })
            }
            LabeledSection(
//                modifier = Modifier.weight(1f, true),
                sectionTitle = "Category", horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {

                println(category)

                CategoriesGrid(mockCategories, selected = category, onSelected = {
                    onSetCategory(it)
                })

            }
            LabeledSection(sectionTitle = "Notes") {

                TextField(modifier = Modifier.fillMaxWidth(1f), singleLine = true, value = notes, onValueChange = {
                    onSetNotes(it)
                })

            }
            LabeledSection(
                sectionTitle = "Date", horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DialogDatePicker(date) {
                    onSetDate(it)
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
        TransactionScreen(amount = "10")
    }
}