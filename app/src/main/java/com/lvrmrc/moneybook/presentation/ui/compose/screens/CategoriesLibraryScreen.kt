package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.data.expenseCategories
import com.lvrmrc.moneybook.data.incomeCategories
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.presentation.ui.compose.components.CategoriesGrid
import com.lvrmrc.moneybook.presentation.ui.compose.components.GridButtonType
import com.lvrmrc.moneybook.presentation.ui.compose.components.LabeledSection
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.ScreenHeader
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.navigateDefault
import com.lvrmrc.moneybook.presentation.viewmodel.AppViewModel

@Composable
fun CategoriesLibraryScreen(
    appVm: AppViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        appVm.setCategories()
    }

    CategoriesLibraryScreen(
        appVm.expenseCategories,
        appVm.incomeCategories,
    )
}

@Composable
private fun CategoriesLibraryScreen(
    expenseCategories: List<Category>, incomeCategories: List<Category>
) {
    val navController = LocalNavController.current
    val previousEntry = navController.previousBackStackEntry
    val fromTransaction = previousEntry?.destination?.route?.startsWith(Screen.Transaction.route) == true
    val selected by remember { mutableStateOf<Category?>(null) }

    /**
     * Navigates to destination or pops back stack depending on previous back stack entry
     */
    fun handleNavigation(category: Category) {
        if (fromTransaction) {
//            selected = category
            previousEntry!!.savedStateHandle["categoryId"] = category.id
            navController.popBackStack()
        } else {
            navController.navigateDefault("${Screen.Category.route}?categoryId=${category.id}")
        }
    }

    Column {
        // Header
        ScreenHeader(stringResource(R.string.categories), colorScheme.primary)
        Column(
            Modifier
                .padding(8.dp)
                .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Expense categories
            LabeledSection(sectionTitle = stringResource(R.string.expense)) {
                CategoriesGrid(expenseCategories,
                    selected = selected, onSelected = {
                        handleNavigation(it)
                    })
            }

            // Income categories
            LabeledSection(sectionTitle = stringResource(R.string.income)) {
                CategoriesGrid(incomeCategories, buttonType = GridButtonType.ADD_NEW, selected = selected, onSelected = {
                    handleNavigation(it)
                })
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CategoriesLibraryScreenPreview(
) {
    NavProvider {
        CategoriesLibraryScreen(expenseCategories, incomeCategories)
    }
}