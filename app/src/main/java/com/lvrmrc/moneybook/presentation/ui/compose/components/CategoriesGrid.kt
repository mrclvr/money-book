package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.data.expenseCategories
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.AppLayout
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.navigateDefault
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen

/**
 * Grid of category items
 */
@Composable
fun CategoriesGrid(
    categories: List<Category>,
    showMore: Boolean = false,
    showAddNew: Boolean = false,
    selected: Category? = null,
    onSelected: (Category) -> Unit = {}
) {
    val navController = LocalNavController.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
//        modifier = Modifier.height(200.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(categories.size) { idx ->
            val category: Category = categories[idx]
            val isSelected: Boolean = category.id == selected?.id
            CategoryGridItem(category, isSelected, onClick = { onSelected(category) })
        }
        if (showMore || showAddNew) {
            item {
                val containerColor = if (showMore) colorScheme.secondaryContainer else colorScheme.primaryContainer
                val contentColor = if (showMore) colorScheme.onSecondaryContainer else colorScheme.background
                val route = if (showMore) Screen.CategoriesLibrary.route else Screen.Category.route
                val icon = if (showMore) Icons.Outlined.MoreHoriz else Icons.Outlined.Add
                val contentDescription =
                    if (showMore) stringResource(R.string.more_categories) else stringResource(R.string.add_new_category)

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .padding(4.dp), contentAlignment = Alignment.TopCenter
                ) {
                    FilledIconButton(modifier = Modifier.size(56.dp),
                        shape = CircleShape,
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = containerColor, contentColor = contentColor
                        ),
                        onClick = {
                            navController.navigateDefault(route)
                        }) {
                        Icon(
                            modifier = Modifier.size(32.dp), imageVector = icon, contentDescription = contentDescription
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun CategoriesGridPreview() {
    NavProvider {
        AppLayout {
            CategoriesGrid(expenseCategories.slice(0..6))
        }
    }
}