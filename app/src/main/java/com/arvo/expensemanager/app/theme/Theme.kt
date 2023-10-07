package com.arvo.expensemanager.app.theme




import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Light Color Scheme
val LightColorScheme = lightColorScheme(
    primary = AppColor300,
    onPrimary = AppColor600,
    primaryContainer = AppColor900,

    secondary = AppColorSec300,
    onSecondary = AppColorSec600,
    secondaryContainer = AppColorSec700,

    tertiary =  colorGreen300,
    onTertiary =  colorGreen600,
    onTertiaryContainer =  colorGreen900,

    error = colorRed300,
    onError = colorRed600,
    onErrorContainer = colorRed900,

    background = colorBackground,
    onBackground = colorBackground,

    surface = CardDark,
    onSurface = CardDark,

    outline = colorOutline,
)

// Dark Color Scheme
val DarkColorScheme = darkColorScheme(
    primary = AppColor300,
    onPrimary = AppColor600,
    primaryContainer = AppColor900,

    secondary = AppColorSec300,
    onSecondary = AppColorSec600,
    secondaryContainer = AppColorSec700,

    tertiary =  colorGreen300,
    onTertiary =  colorGreen600,
    onTertiaryContainer =  colorGreen900,

    error = colorRed300,
    onError = colorRed600,
    onErrorContainer = colorRed900,

    background = darkColorBackground,
    onBackground = darkColorBackground,

    surface = CardLight,
    onSurface = CardLight,

    outline = colorOutline,
)

@Composable
fun ExpenseManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme
    val typography = if (darkTheme) DarkTypography else LightTypography
    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
    content()
}
