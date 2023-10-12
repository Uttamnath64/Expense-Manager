package com.arvo.expensemanager.app.theme

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Light Color Scheme
@SuppressLint("ConflictingOnColor")
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
@SuppressLint("ConflictingOnColor")
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


val ExpenseManagerColor: ColorScheme
    @Composable get() = MaterialTheme.colorScheme

val ExpenseManagerShapes: Shapes
    @Composable get() = MaterialTheme.shapes

val ExpenseManagerTypography: Typography
    @Composable get() = MaterialTheme.typography


@Composable
fun ExpenseManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
//    val colors = if (darkTheme) DarkColorScheme else LightColorScheme
    val colors =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (darkTheme) DarkColorScheme else LightColorScheme
    }
    val typography = if (darkTheme) DarkTypography else LightTypography

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}
