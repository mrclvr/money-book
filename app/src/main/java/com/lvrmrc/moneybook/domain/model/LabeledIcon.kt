package com.lvrmrc.moneybook.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Locale

sealed class LabeledIcon(val label: IconLabel, val icon: ImageVector) {
//
//    data object MonitorHeart : LabeledIcon(IconLabel.MonitorHeart, Icons.Outlined.MonitorHeart)
//    data object House : LabeledIcon(IconLabel.House, Icons.Outlined.House)
//    data object School : LabeledIcon(IconLabel.School, Icons.Outlined.School)
//    data object ShoppingBasket : LabeledIcon(IconLabel.ShoppingBasket, Icons.Outlined.ShoppingBasket)
//    data object ShoppingCart : LabeledIcon(IconLabel.ShoppingCart, Icons.Outlined.ShoppingCart)
//    data object DirectionsCar : LabeledIcon(IconLabel.DirectionsCar, Icons.Outlined.DirectionsCar)
//    data object LocalGasStation : LabeledIcon(IconLabel.LocalGasStation, Icons.Outlined.LocalGasStation)
//    data object ReceiptLong : LabeledIcon(IconLabel.ReceiptLong, Icons.Outlined.ReceiptLong)
//
//
//    companion object {
//        fun getIcon(label: IconLabel): ImageVector {
//            return LabeledIcon::class.sealedSubclasses.map { it.objectInstance as LabeledIcon }.singleOrNull { it.label == label }.let {
//                when (it) {
//                    null -> Icons.Rounded.Payments
//                    else -> it.icon
//                }
//            }
//        }
//    }
//
}

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
