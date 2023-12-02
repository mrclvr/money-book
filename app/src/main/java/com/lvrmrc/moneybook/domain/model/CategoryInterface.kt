package com.lvrmrc.moneybook.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


interface CategoryInterface {
    val label: String
    val icon: ImageVector
    val color: Color
}