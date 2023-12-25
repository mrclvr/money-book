package com.lvrmrc.moneybook.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Defines a tab item for tabs navigation
 */
interface TabItemInterface {
    val title: String
    val icon: ImageVector
    val content: @Composable () -> Unit
    val onClick: suspend () -> Unit
}

