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
    categories: List<Category>, selected: Category? = null, onSelected: (Category) -> Unit = {}
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
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)
                    .padding(4.dp), contentAlignment = Alignment.TopCenter
            ) {
                FilledIconButton(modifier = Modifier.size(56.dp), shape = CircleShape, colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = colorScheme.primaryContainer, contentColor = colorScheme.background
                ), onClick = {
                    navController.navigateDefault(Screen.Category.route)
                }) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Outlined.Add,
                        contentDescription = stringResource(R.string.add_new_category)
                    )
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