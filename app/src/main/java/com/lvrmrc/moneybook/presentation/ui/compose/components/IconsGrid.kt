package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.lvrmrc.moneybook.R
import com.lvrmrc.moneybook.data.outlinedIcons
import com.lvrmrc.moneybook.domain.model.IconLabel
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.AppLayout
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.CustomGrid
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.GridButtonType
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.GridMoreButton
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.screens.Screen

/**
 * Grid of category items
 */
@Composable
fun IconsGrid(
    icons: List<Pair<IconLabel, ImageVector>>,
    showMore: Boolean = false,
    selectedColor: Color? = null,
    selected: ImageVector? = null,
    onSelected: (ImageVector) -> Unit = {}
) {
    CustomGrid(columns = 4, itemsCount = icons.size, button = {
        if (showMore) {
            GridMoreButton(
                Modifier, Alignment.Center, GridButtonType.SHOW_MORE, stringResource(R.string.more_icons), Screen.IconsLibrary.route
            )
        }
    }) {
        val icon = icons[it].second
        val isSelected: Boolean = icon == selected
        IconGridItem(icon, selectedColor, isSelected, onClick = { onSelected(icon) })
    }
}

@Composable
@Preview
fun IconsGridPreview() {
    NavProvider {
        AppLayout {
            IconsGrid(outlinedIcons.toList(), true)
        }
    }
}