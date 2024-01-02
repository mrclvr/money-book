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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.data.expenseCategories
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.presentation.ui.compose.components.CategoryGridItem
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.navigateDefault

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
fun GridMoreButton(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    type: GridButtonType = GridButtonType.SHOW_MORE,
    contentDescription: String,
    route: String
) {
    val navController = LocalNavController.current
    val containerColor = MaterialTheme.colorScheme.primaryContainer
    val contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    val icon = if (type == GridButtonType.SHOW_MORE) Icons.Outlined.MoreHoriz else Icons.Outlined.Add

    Box(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .then(modifier), contentAlignment = contentAlignment
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
        CustomGrid(columns = 4,
            itemsCount = categories.size,
            button = { GridMoreButton(Modifier.padding(4.dp), Alignment.TopCenter, GridButtonType.ADD_NEW, "null", "null") }) {
            val category: Category = categories[it]
            CategoryGridItem(category, false, onClick = { })
        }
    }
}