package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.data.expenseCategories
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.CustomGrid
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.GridButtonType
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.GridMoreButton
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen

/**
 * Grid of category items
 */
@Composable
fun CategoriesGrid(
    categories: List<Category>, buttonType: GridButtonType? = null, selected: Category? = null, onSelected: (Category) -> Unit = {}
) {
    CustomGrid(columns = 4, itemsCount = categories.size, verticalArrangement = Arrangement.spacedBy(10.dp), button = {
        if (buttonType != null) {
            val isShowMore = buttonType == GridButtonType.SHOW_MORE
            val route = if (isShowMore) Screen.CategoriesLibrary.route else Screen.Category.route
            val contentDescription = if (isShowMore) stringResource(R.string.more_categories) else stringResource(R.string.add_new_category)
            GridMoreButton(Modifier.padding(4.dp), Alignment.TopCenter, buttonType, contentDescription, route)
        }
    }) {
        val category: Category = categories[it]
        val isSelected: Boolean = category.id == selected?.id
        CategoryGridItem(category, isSelected, onClick = { onSelected(category) })
    }
}


@Composable
@Preview(showBackground = true)
fun CategoriesGridPreview() {
    NavProvider {
        CategoriesGrid(expenseCategories.slice(0..6))
    }
}