package com.lvrmrc.moneybook.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


data class CategoryWithTransactions(
    override val label: String,
    override val icon: ImageVector,
    override val color: Color,
    override val lightText: Boolean,
    val transactions: List<Transaction>,
    val total: Double
) : CategoryInterface