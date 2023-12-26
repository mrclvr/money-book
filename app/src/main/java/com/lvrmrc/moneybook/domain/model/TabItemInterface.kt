package com.lvrmrc.moneybook.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Defines a tab item for tabs navigation
 */
interface TabItemInterface {
    val title: String
    val icon: ImageVector
    val onClick: () -> Unit
    val content: @Composable () -> Unit
}

