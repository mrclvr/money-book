package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.lvrmrc.moneybook.data.colorsMap

/**
 * Row of default colors
 */
@Composable
fun ColorsRow(
    selected: Color? = null, onSelected: (Color) -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
    ) {
        colorsMap.forEach { color ->
            ColorsRowItem(color.value, color.value == selected, onClick = { onSelected(color.value) })
        }
    }
}

@Composable
@Preview
fun ColorsRowPreview() {
    ColorsRow()
}