package com.arvo.expensemanager.app.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with

val DarkTypography = Typography(
        headlineLarge = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.Black,
                color = White,
                fontSize = 28.sp
        ),
        headlineMedium = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.W900,
                color = White,
                fontSize = 22.sp
        ),
        headlineSmall = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.Medium,
                color = White,
                fontSize = 18.sp
        ),
        titleLarge = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.Bold,
                color = White,
                fontSize = 22.sp
        ),
        titleMedium = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.SemiBold,
                color = White,
                fontSize = 18.sp
        ),
        titleSmall = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.Normal,
                color = White,
                fontSize = 16.sp
        ),
        bodyLarge = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.W900,
                color = White,
                fontSize = 18.sp
        ),
        bodyMedium = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.SemiBold,
                color = White,
                fontSize = 16.sp
        ),
        bodySmall = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.Medium,
                color = White,
                fontSize = 14.sp
        ),
        labelLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                color = White,
                fontSize = 14.sp
        ),
        labelMedium = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Light,
                color = White,
                fontSize = 12.sp
        ),
        labelSmall = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.ExtraLight,
                color = White,
                fontSize = 10.sp
        )
)

// set of light material typography styles to start with.
val LightTypography = Typography(
        headlineLarge = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.Black,
                color = Black,
                fontSize = 28.sp
        ),
        headlineMedium = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.W900,
                color = Black,
                fontSize = 22.sp
        ),
        headlineSmall = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.Medium,
                color = Black,
                fontSize = 18.sp
        ),
        titleLarge = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.Bold,
                color = Black,
                fontSize = 22.sp
        ),
        titleMedium = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.SemiBold,
                color = Black,
                fontSize = 18.sp
        ),
        titleSmall = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.Normal,
                color = Black,
                fontSize = 16.sp
        ),
        bodyLarge = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.W900,
                color = Black,
                fontSize = 18.sp
        ),
        bodyMedium = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.SemiBold,
                color = Black,
                fontSize = 16.sp
        ),
        bodySmall = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.Medium,
                color = Black,
                fontSize = 14.sp
        ),
        labelLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                color = Black,
                fontSize = 14.sp
        ),
        labelMedium = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Light,
                color = Black,
                fontSize = 12.sp
        ),
        labelSmall = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.ExtraLight,
                color = Black,
                fontSize = 10.sp
        )
)
