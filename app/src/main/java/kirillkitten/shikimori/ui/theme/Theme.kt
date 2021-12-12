package kirillkitten.shikimori.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Gray,
    primaryVariant = DarkGreen,
    secondary = SeaBlue,
    secondaryVariant = DarkBlue,
    background = DarkWhite,
    surface = DarkWhite,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = DarkPurple,
    onSurface = DarkPurple,
    error = Red,
    onError = Color.White
)

private val DarkColorPalette = darkColors(
    primary = Turquoise,
    primaryVariant = DarkGray,
    secondary = LightBlue,
    background = DarkPurple,
    surface = DarkPurple,
    onPrimary = DarkTeal,
    onSecondary = OceanBlue,
    onBackground = LightGray,
    onSurface = LightGray,
    error = Melon,
    onError = Burgundy
)

@Composable
fun ShikimoriTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
