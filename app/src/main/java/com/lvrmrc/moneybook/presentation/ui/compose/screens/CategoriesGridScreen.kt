package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.data.expenseCategories
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.presentation.ui.compose.components.CategoriesGrid
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.ScreenHeader
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.navigateDefault
import com.lvrmrc.moneybook.presentation.viewmodel.AppViewModel

@Composable
fun CategoriesGridScreen(
    appVm: AppViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        appVm.setCategories()
    }

    CategoriesGridScreen(
        appVm.categories
    )
}

@Composable
private fun CategoriesGridScreen(
    categories: List<Category>
) {
    val navController = LocalNavController.current

    Column {
        ScreenHeader(stringResource(R.string.categories), colorScheme.secondary)
        Column(Modifier.padding(15.dp)) {
            CategoriesGrid(categories, onSelected = { navController.navigateDefault("${Screen.Category.route}?categoryId=${it.id}") })
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CCategoriesGridScreenPreview(
) {
    NavProvider {
        CategoriesGridScreen(expenseCategories)
    }
}