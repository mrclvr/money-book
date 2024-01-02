package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.lvrmrc.moneybook.data.colorsMap
import com.lvrmrc.moneybook.presentation.ui.compose.components.layout.CustomGrid

/**
 * Row of default colors
 */
@Composable
fun ColorsGrid(
    selected: Color? = null, onSelected: (Color) -> Unit = {}
) {
    val colors = colorsMap.toList()
    CustomGrid(
        modifier = Modifier.fillMaxWidth(), columns = 8, itemsCount = colorsMap.size
    ) {
        val color = colors[it].second
        ColorsRowItem(color, color == selected, onClick = { onSelected(color) })

    }
}

@Composable
@Preview
fun ColorsRowPreview() {
    ColorsGrid()
}