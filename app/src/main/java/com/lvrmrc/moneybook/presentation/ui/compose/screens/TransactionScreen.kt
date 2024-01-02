package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.data.expenseCategories
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.TransactionType
import com.lvrmrc.moneybook.presentation.ui.compose.components.CategoriesGrid
import com.lvrmrc.moneybook.presentation.ui.compose.components.DatePickerDialog
import com.lvrmrc.moneybook.presentation.ui.compose.components.LabeledSection
import com.lvrmrc.moneybook.presentation.ui.compose.components.TransactionTypeRadio
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.FABLayout
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.GridButtonType
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.ScreenHeader
import com.lvrmrc.moneybook.presentation.viewmodel.AppViewModel
import com.lvrmrc.moneybook.presentation.viewmodel.TransactionViewModel
import com.lvrmrc.moneybook.utils.NumberUtils
import com.lvrmrc.moneybook.utils.localFormat
import java.time.LocalDateTime
import java.util.UUID
import kotlin.math.min

@Composable
fun TransactionScreen(
    categoryId: UUID?, appVm: AppViewModel = hiltViewModel(), vm: TransactionViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current

    LaunchedEffect(key1 = categoryId) {
        categoryId?.let {
            vm.loadCategory(categoryId)
            vm.category?.type?.let { appVm.setTransType(it) }
        }
    }

    val currentCategories = if (appVm.transType == TransactionType.EXPENSE) appVm.expenseCategories else appVm.incomeCategories

    TransactionScreen(
        amount = vm.amount,
        notes = vm.notes,
        date = vm.date,
        category = vm.category,
        transType = appVm.transType,
        categories = currentCategories,
        isUpdate = vm.transactionId != null,
        fabEnabled = vm.fabEnabled.value,
        onSetAmount = { vm.setAmount(it) },
        onSetNotes = { vm.setNotes(it) },
        onSetDate = { vm.setDate(it) },
        onSetCategory = { vm.setCategory(it) },
        onSetType = {
            appVm.setTransType(it)
            vm.setCategory(null)
        },
        onUpdate = {
            vm.addTransaction(appVm.transType)
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
    transType: TransactionType,
    categories: List<Category>,
    isUpdate: Boolean = false,
    fabEnabled: Boolean = false,
    onSetAmount: (String) -> Unit = {},
    onSetNotes: (String) -> Unit = {},
    onSetDate: (LocalDateTime) -> Unit = {},
    onSetCategory: (Category?) -> Unit = {},
    onSetType: (TransactionType) -> Unit = {},
    onUpdate: () -> Unit = {}
) {
    val fabText = if (isUpdate) R.string.update else R.string.add_transaction
    val headerText = if (isUpdate) R.string.update_transaction else R.string.add_new_transaction
    val toIndex = min(categories.size, 6)
    val updatedCategories = categories.subList(0, toIndex).toMutableList()

    println("LOADED")

    /**
     * Replace first category with selected if not visible and change type accordingly
     */
    category?.let {
        if (updatedCategories.find { it == category } == null) {
            updatedCategories[0] = category
        }
    }

    FABLayout(topBar = {
        ScreenHeader(
            title = stringResource(
                headerText
            )
        )
    }, fabText = stringResource(fabText), fabEnabled = fabEnabled, onFabAction = { onUpdate() }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            // Amount
            LabeledSection(horizontalArrangement = Arrangement.Center) {
                TextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    value = amount,
                    onValueChange = {
                        onSetAmount(NumberUtils.cleanDoubleText(it))
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    prefix = { Text(stringResource(R.string.eur)) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = colorScheme.primary,
                        unfocusedIndicatorColor = colorScheme.primary,
                    )
                )
            }

            // Type
            LabeledSection(
                sectionTitle = stringResource(R.string.type)
            ) {
                TransactionTypeRadio(transType, onSelected = { onSetType(it) })
            }

            // Date
            LabeledSection(
                sectionTitle = stringResource(R.string.date), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    DatePickerDialog(date) {
                        onSetDate(it)
                    }

                    date.localFormat()?.let { Text(text = it) }
                }
            }

            // Notes
            LabeledSection(sectionTitle = stringResource(R.string.notes)) {
                TextField(modifier = Modifier.fillMaxWidth(1f),
                    singleLine = true,
                    value = notes,
                    maxLines = 1,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = colorScheme.primary,
                        unfocusedIndicatorColor = colorScheme.primary,
                    ),
                    onValueChange = {
                        if (it.length <= 50) onSetNotes(it)
                    })
            }

            // Category
            LabeledSection(
//                modifier = Modifier.weight(1f, true),
                sectionTitle = stringResource(R.string.category), horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                CategoriesGrid(updatedCategories, buttonType = GridButtonType.SHOW_MORE, selected = category, onSelected = {
                    onSetCategory(it)
                })
            }
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TransactionScreenPreview(
) {
    NavProvider {
        TransactionScreen(transType = TransactionType.INCOME, categories = expenseCategories)
    }
}