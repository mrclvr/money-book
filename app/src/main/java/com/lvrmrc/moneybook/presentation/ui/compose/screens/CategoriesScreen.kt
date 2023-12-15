package com.lvrmrc.moneybook.presentation.ui.compose.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvrmrc.moneybook.data.mockCategories
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.presentation.ui.compose.components.CategoriesGrid
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.AppLayout

@Composable
fun CategoriesScreen(
    navController: NavHostController = rememberNavController()
) {
    CategoriesScreen(
        navController, mockCategories
    )
}

@Composable
private fun CategoriesScreen(
    navController: NavHostController = rememberNavController(), categories: List<Category>
) {

    CategoriesGrid(categories)
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CategoriesScreenPreview(
) {
    AppLayout {
        CategoriesScreen(categories = mockCategories)
    }
}