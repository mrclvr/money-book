package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.data.expenseCategories
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.presentation.ui.compose.components.CategoriesGrid
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.AppLayout
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.ScreenHeader
import com.lvrmrc.moneybook.presentation.viewmodel.AppViewModel

@Composable
fun CategoriesScreen(
    appVm: AppViewModel = hiltViewModel()
) {
    CategoriesScreen(
        appVm.categories
    )
}

@Composable
private fun CategoriesScreen(
    categories: List<Category>
) {
    Column {
        ScreenHeader(stringResource(R.string.categories), colorScheme.secondary)
        Column(Modifier.padding(15.dp)) {
            CategoriesGrid(categories)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CategoriesScreenPreview(
) {
    AppLayout {
        CategoriesScreen(expenseCategories)
    }
}