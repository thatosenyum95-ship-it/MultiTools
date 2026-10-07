package com.multitools.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val MultiToolsColors = lightColorScheme()

@Composable
fun MultiToolsTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = MultiToolsColors, content = content)
}
