package com.lvrmrc.moneybook.presentation.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat

private val lightColors = lightColorScheme(
    primary = primary,
    onPrimary = primary50,
    primaryContainer = primary100,
    onPrimaryContainer = primary900,
    inversePrimary = primary200,
    secondary = complementary,
    onSecondary = complementary50,
    secondaryContainer = complementary100,
    onSecondaryContainer = complementary900,
    tertiary = triadic2,
    onTertiary = triadic2_50,
    tertiaryContainer = triadic2_100,
    onTertiaryContainer = triadic2_900,
    surfaceTint = primary,
)

private val darkColors = darkColorScheme(
)

@Composable
fun MoneyBookTheme(useDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (useDarkTheme) {
        darkColors
    } else {
        lightColors
    }

    val view = LocalView.current
    val statusBarColor = colorScheme.secondary.toArgb()

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = statusBarColor
//            window.statusBarColor = if (darkTheme) Color.Black.toArgb() else Color.Red.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = useDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = colors, typography = Typography, shapes = Shapes, content = content
    )
}

@Composable
@Preview(showSystemUi = true, uiMode = 2)
fun MoneyBookThemePreview() {
    MoneyBookTheme {}
}
