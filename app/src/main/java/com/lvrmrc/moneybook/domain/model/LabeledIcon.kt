package com.lvrmrc.moneybook.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Payments
import androidx.compose.material.icons.rounded.School
import androidx.compose.ui.graphics.vector.ImageVector

sealed class LabeledIcon(val label: Label, val icon: ImageVector) {

    data object School : LabeledIcon(Label.School, Icons.Rounded.School)

    companion object {
        fun getIcon(label: Label): ImageVector {
            return LabeledIcon::class.sealedSubclasses.map { it.objectInstance as LabeledIcon }.singleOrNull { it.label == label }.let {
                when (it) {
                    null -> Icons.Rounded.Payments
                    else -> it.icon
                }
            }
        }
    }

    enum class Label {
        School
    }
}