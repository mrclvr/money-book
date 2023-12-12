package com.lvrmrc.moneybook.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.UUID


data class CategoryWithTransactions(
    override val id: UUID,
    override val label: String,
    override val icon: ImageVector,
    override val color: Color,
    override val lightText: Boolean,
    var transactions: ArrayList<Transaction> = arrayListOf(),
    var total: Double = 0.0
) : CategoryInterface {

    fun toCategory() = Category(id, label, icon, color, lightText)
}
