package kirillkitten.shikimori.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Gray800,
    primaryVariant = Gray900,
    secondary = Azure700,
    secondaryVariant = Azure500,
    background = Gray50,
    surface = Gray50,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Gray900,
    onSurface = Gray900,
)

private val DarkColorPalette = darkColors(
    primary = Gray200,
    primaryVariant = Gray900,
    secondary = Azure200
)

@Composable
fun ShikimoriTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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

/**
 * Provides border stroke color depending on light or dark theme.
 */
@Composable
fun outlineColor(): Color = if (!isSystemInDarkTheme()) Azure500 else Azure600
