package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.CustomGrid
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.navigateDefault
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
            GridButton(buttonType)
        }
    }) {
        val category: Category = categories[it]
        val isSelected: Boolean = category.id == selected?.id
        CategoryGridItem(category, isSelected, onClick = { onSelected(category) })
    }
}

enum class GridButtonType {
    ADD_NEW, SHOW_MORE
}

@Composable
fun GridButton(type: GridButtonType = GridButtonType.SHOW_MORE) {
    val navController = LocalNavController.current
    val isShowMore = type == GridButtonType.SHOW_MORE

    val containerColor = if (isShowMore) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.primaryContainer
    val contentColor = if (isShowMore) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.background
    val route = if (isShowMore) Screen.CategoriesLibrary.route else Screen.Category.route
    val icon = if (isShowMore) Icons.Outlined.MoreHoriz else Icons.Outlined.Add
    val contentDescription = if (isShowMore) stringResource(R.string.more_categories) else stringResource(R.string.add_new_category)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .padding(4.dp), contentAlignment = Alignment.TopCenter
    ) {
        FilledIconButton(modifier = Modifier.size(56.dp), shape = CircleShape, colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = containerColor, contentColor = contentColor
        ), onClick = {
            navController.navigateDefault(route)
        }) {
            Icon(
                modifier = Modifier.size(32.dp), imageVector = icon, contentDescription = contentDescription
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CategoriesGridPreview() {
    NavProvider {
        CategoriesGrid(expenseCategories.slice(0..6))
    }
}