package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.lvrmrc.moneybook.data.mockCategories
import com.lvrmrc.moneybook.domain.model.Category

@Composable
fun CategoriesGrid(categories: List<Category>, selected: Category? = null, onSelected: (Category) -> Unit = {}) {

    val current: MutableState<Category?> = remember { mutableStateOf(selected) }

    fun onClick(category: Category) {
        current.value = if (current.value?.id == category.id) null else category
        onSelected(category)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(4)
    ) {
        items(categories.size) { idx ->
            val category: Category = categories[idx]
            val isSelected: Boolean = category.id == current.value?.id
            println(isSelected)
            CategoryItem(category, isSelected, onClick = { onClick(category) })
        }
    }


}

@Composable
@Preview
fun CategoriesGridPreview() {
//    AppLayout() {
    CategoriesGrid(mockCategories)
//    }
}