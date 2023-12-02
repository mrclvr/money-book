package com.lvrmrc.moneybook.presentation.ui.compose.components.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.lvrmrc.moneybook.domain.model.TransactionType

data class TabItem(
    val title: String,
    val icon: ImageVector,
    val content: @Composable () -> Unit = {},
    val onClick: suspend () -> Unit = {},
    val type: TransactionType? = null
)

