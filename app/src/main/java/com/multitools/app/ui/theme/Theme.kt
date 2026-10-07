package com.multitools.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF315EF6),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDDE5FF),
    onPrimaryContainer = Color(0xFF00164F),
    secondary = Color(0xFF6C4DDB),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE9DDFF),
    onSecondaryContainer = Color(0xFF25005A),
    tertiary = Color(0xFF008F8C),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFB7F3EE),
    onTertiaryContainer = Color(0xFF00201E),
    background = Color(0xFFF7F8FC),
    surface = Color(0xFFF7F8FC),
    surfaceVariant = Color(0xFFE9ECF4),
    onSurface = Color(0xFF171A22),
    onSurfaceVariant = Color(0xFF5E6472)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFB7C6FF),
    onPrimary = Color(0xFF00277A),
    primaryContainer = Color(0xFF1744C7),
    onPrimaryContainer = Color(0xFFDDE5FF),
    secondary = Color(0xFFD0BCFF),
    onSecondary = Color(0xFF38206E),
    secondaryContainer = Color(0xFF5136A0),
    onSecondaryContainer = Color(0xFFE9DDFF),
    tertiary = Color(0xFF71D7D1),
    onTertiary = Color(0xFF003735),
    tertiaryContainer = Color(0xFF00504D),
    onTertiaryContainer = Color(0xFFB7F3EE),
    background = Color(0xFF0D1017),
    surface = Color(0xFF0D1017),
    surfaceVariant = Color(0xFF242833),
    onSurface = Color(0xFFE6E8F0),
    onSurfaceVariant = Color(0xFFC3C7D2)
)

@Composable
fun MultiToolsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content
    )
}
