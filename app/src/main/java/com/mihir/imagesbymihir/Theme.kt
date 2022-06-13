package com.mihir.imagesbymihir

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val DarkColorPalette = darkColors(
    primary = Color(0xffcdffad),
    primaryVariant = Color(0xff79f22c),
    background = Color(0xff1a1a1a)
)


private val LightColorPalette = lightColors(
    primary = Color(0xffcdffad),
    primaryVariant = Color(0xff79f22c),
    background = Color(0xff1a1a1a)
)
@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content : @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}