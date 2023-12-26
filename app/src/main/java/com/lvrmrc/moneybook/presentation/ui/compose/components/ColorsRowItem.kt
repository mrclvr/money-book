package com.lvrmrc.moneybook.presentation.ui.compose.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvrmrc.moneybook.presentation.ui.theme.primary500

@Composable
fun ColorsRowItem(
    color: Color, selected: Boolean = false, onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .height(36.dp)
            .aspectRatio(1f)
            .background(color, CircleShape)
            .border(BorderStroke(2.dp, colorScheme.background), CircleShape)
            .clickable { onClick() }, contentAlignment = Alignment.Center
    ) {
        if (selected) {
            Icon(Icons.Rounded.Check, "Selected", tint = colorScheme.background)
        }
    }
}

@Composable
@Preview
fun ColorsRowItemPreview() {
    ColorsRowItem(primary500, true)
}