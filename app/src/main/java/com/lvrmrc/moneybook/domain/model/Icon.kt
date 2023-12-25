package com.lvrmrc.moneybook.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Locale

enum class IconLabel {
    MonitorHeart, House, School, ShoppingBasket, ShoppingCart, DirectionsCar, LocalGasStation, ReceiptLong, LocalBar
}

enum class IconStyle {
    Outlined, Filled, Rounded, Default
}

fun getIconByName(label: IconLabel, style: IconStyle): ImageVector? {
    return try {
        val styleClass: Any = when (style) {
            IconStyle.Filled -> Icons.Filled
            IconStyle.Outlined -> Icons.Outlined
            IconStyle.Rounded -> Icons.Rounded
            else -> Icons.Default
        }

        val styleString = style.name.lowercase(Locale.ROOT)
        val iconClass = Class.forName("androidx.compose.material.icons.${styleString}.${label.name}Kt")
        val method = iconClass.declaredMethods.first()
        method.invoke(null, styleClass) as ImageVector
    } catch (_: Throwable) {
        null
    }
}

fun getIconsList(style: IconStyle): Map<IconLabel, ImageVector> {
    val icons = mutableMapOf<IconLabel, ImageVector>()
    IconLabel.entries.forEach {
        getIconByName(it, style)?.let { found -> icons[it] = found }
    }
    return icons
}

val defaultIcons: Map<IconLabel, ImageVector> = getIconsList(IconStyle.Default)
val outlinedIcons: Map<IconLabel, ImageVector> = getIconsList(IconStyle.Outlined)
val filledIcons: Map<IconLabel, ImageVector> = getIconsList(IconStyle.Filled)
val roundedIcons: Map<IconLabel, ImageVector> = getIconsList(IconStyle.Rounded)