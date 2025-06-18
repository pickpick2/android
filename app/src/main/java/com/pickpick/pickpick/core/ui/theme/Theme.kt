package com.pickpick.pickpick.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val LightColorScheme = lightColorScheme(
    primary = PrimaryDefault,
    onPrimary = White,

    secondary = G500,
    onSecondary = White,

    tertiary = PrimaryDefault,
    onTertiary = White,

    background = White,
    onBackground = G900,

    surface = White,
    onSurface = G900
)

@Composable
fun PickPickTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
