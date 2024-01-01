package com.lvrmrc.moneybook.presentation.ui.compose.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.lvrmrc.moneybook.presentation.ui.compose.components.CategoryGridItem
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.navigateDefault
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen

@Composable
fun CustomGrid(
    modifier: Modifier = Modifier,
    columns: Int,
    itemsCount: Int,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    button: @Composable() () -> Unit = {},
    content: @Composable() (Int) -> Unit
) {
    Column(modifier = modifier, verticalArrangement = verticalArrangement, horizontalAlignment = horizontalAlignment) {
        var rows = (itemsCount / columns)

        if (itemsCount.mod(columns) > 0) {
            rows += 1
        }

        for (rowId in 0 until rows) {
            val firstIndex = rowId * columns

            Row {
                for (columnId in 0 until columns) {
                    val index = firstIndex + columnId
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .aspectRatio(1f)
                    ) {
                        if (index < itemsCount) {
                            content(index)
                        } else if (index == itemsCount && button != {}) {
                            button()
                        }
                    }
                }
            }
        }
    }
}

enum class GridButtonType {
    ADD_NEW, SHOW_MORE
}

@Composable
fun MoreButton(type: GridButtonType = GridButtonType.SHOW_MORE) {
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
fun CustomGridPreview() {
    NavProvider {
        val categories = expenseCategories.slice(0..6)
        CustomGrid(columns = 4, itemsCount = categories.size, button = { MoreButton() }) {
            val category: Category = categories[it]
            CategoryGridItem(category, false, onClick = { })
        }
    }
}