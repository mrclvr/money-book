package com.lvrmrc.moneybook.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Locale

/**
 * Icon names identifiers
 */
enum class IconLabel {
    DEFAULT, MonitorHeart, House, School, FitnessCenter, Pool, SportsBasketball, VideogameAsset, ShoppingBasket, ShoppingCart, PedalBike, DirectionsCar, Train, LocalGasStation, ReceiptLong, LocalActivity, LocalBar, LocalDining, LunchDining, FlightTakeoff, RequestPage, AccountBalance, AccountBalanceWallet, Savings, Redeem
}

/**
 * Icon styles identifiers
 */
enum class IconStyle {
    Outlined, Filled, Rounded, Default
}

/**
 * Gets icon with given name and style from Material icons library
 *
 * @param label the name of the icon
 * @param style the style of the icon
 */
fun getIconByName(label: IconLabel, style: IconStyle): ImageVector? {
    return try {
        if (label == IconLabel.DEFAULT) return Icons.Filled.QuestionMark

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

/**
 * Maps each IconLabel to corresponding IconsVector with given style
 *
 * @param style the style of the icons
 */
fun getIconsMap(style: IconStyle, labels: List<IconLabel> = IconLabel.entries): Map<IconLabel, ImageVector> {
    val icons = mutableMapOf<IconLabel, ImageVector>()
    labels.forEach {
        getIconByName(it, style).let { found -> if (found !== null) icons[it] = found }
    }
    return icons
}

fun getIconLabel(iconsMap: Map<IconLabel, ImageVector>, icon: ImageVector): IconLabel =
    iconsMap.entries.find { it.value == icon }?.key ?: IconLabel.DEFAULT

