package com.lvrmrc.moneybook.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


data class Category(
    override val label: String, override val icon: ImageVector, override val color: Color, override val lightText: Boolean
) : CategoryInterface