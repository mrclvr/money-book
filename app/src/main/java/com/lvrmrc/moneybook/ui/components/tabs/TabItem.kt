package com.lvrmrc.moneybook.ui.components.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val title: String, val icon: ImageVector, val content: @Composable () -> Unit, val onClick: () -> Unit = {}
)
