package com.lvrmrc.moneybook.presentation.ui.compose.components.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

interface TabItemInterface {
    val title: String
    val icon: ImageVector
    val content: @Composable () -> Unit
    val onClick: suspend () -> Unit
}

