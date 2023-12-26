package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.LocalNavController
import com.lvrmrc.moneybook.domain.model.IconLabel
import com.lvrmrc.moneybook.domain.model.outlinedIcons
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.AppLayout
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.NavProvider
import com.lvrmrc.moneybook.presentation.ui.compose.navigation.navigateDefault
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
    val navController = LocalNavController.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(4)
    ) {
        items(icons) { icon ->
            val isSelected: Boolean = icon.second == selected
            IconGridItem(icon.second, selectedColor, isSelected, onClick = { onSelected(icon.second) })
        }
//        items(icons.size) { idx ->
//            val iconLabel: IconLabel = IconLabel.entries[idx]
//            val icon: ImageVector? = icons[iconLabel]
//            val isSelected: Boolean = icon == selected
//
//            if (icon != null) {
//                IconGridItem(icon, selectedColor, isSelected, onClick = { onSelected(icon) })
//            }
//        }

        if (showMore) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f), contentAlignment = Alignment.Center
                ) {
                    FilledIconButton(modifier = Modifier.size(56.dp),
                        shape = CircleShape,
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        ),
                        onClick = {
                            navController.navigateDefault(Screen.IconsLibrary.route)
                        }) {
                        Icon(modifier = Modifier.size(32.dp), imageVector = Icons.Outlined.MoreHoriz, contentDescription = "More icons")
                    }
                }
            }
        }
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