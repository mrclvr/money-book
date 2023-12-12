package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.data.mockCategories
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.presentation.ui.compose.layouts.AppLayout

@Composable
fun CategoriesGrid(categories: List<Category>, selected: Category? = null, onSelected: (Category) -> Unit = {}) {

    val current: MutableState<Category?> = remember { mutableStateOf(selected) }

    fun onClick(category: Category) {
        current.value = if (current.value?.id == category.id) null else category
        onSelected(category)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(5), modifier = Modifier.height(160.dp)
    ) {
        items(categories.size) { idx ->
            val category: Category = categories[idx]
            val isSelected: Boolean = category.id == current.value?.id
            CategoryItem(category, isSelected, onClick = { onClick(category) })
        }
    }


}

@Composable
@Preview
fun CategoriesGridPreview() {
    AppLayout() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),

            ) {
            CategoriesGrid(mockCategories)
        }
    }
}