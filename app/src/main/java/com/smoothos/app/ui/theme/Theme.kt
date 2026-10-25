package com.smoothos.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val SmoothColorScheme = darkColorScheme(
    primary = SmoothAccent,
    secondary = SmoothAccent2,
    tertiary = SmoothPink,
    background = SmoothDark,
    surface = SmoothSurface,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = SmoothText,
    onSurface = SmoothText,
    surfaceVariant = SmoothCard,
    onSurfaceVariant = SmoothTextDim
)

@Composable
fun SmoothOSTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = SmoothColorScheme,
        content = content
    )
}
