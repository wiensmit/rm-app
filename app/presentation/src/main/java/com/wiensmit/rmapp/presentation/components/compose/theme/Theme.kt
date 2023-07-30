package com.wiensmit.rmapp.presentation.components.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Immutable
data class AppColors(
    val background: Color = Color.Unspecified,
    val inverseBackground: Color = Color.Unspecified,
    val onBackground: Color = Color.Unspecified,
    val onInverseBackground: Color = Color.Unspecified,
    val surface: Color = Color.Unspecified,
    val onSurface: Color = Color.Unspecified,
    val divider: Color = Color.Unspecified,
    val neutral: Color = Color.Unspecified,
)

val LightColors = AppColors(
    background = PrimaryWhite,
    inverseBackground = NearBlack,
    onBackground = PrimaryBlack,
    onInverseBackground = PrimaryWhite,
    surface = Grey5,
    onSurface = PrimaryBlack,
    divider = Grey4,
    neutral = Grey2,
)

val DarkColors = AppColors(
    background = NearBlack,
    inverseBackground = PrimaryWhite,
    onBackground = PrimaryWhite,
    onInverseBackground = PrimaryBlack,
    surface = Grey1,
    onSurface = PrimaryWhite,
    divider = Grey4,
    neutral = Grey5,
)

private val Typography = AppTypography(
    heading = TypographyToken.header,
    subtitle = TypographyToken.subtitle,
    body = TypographyToken.body,
    bodyBold = TypographyToken.bodyBold,
    button = TypographyToken.button
)

@Immutable
data class AppTypography(
    val heading: TextStyle = TextStyle.Default,
    val subtitle: TextStyle = TextStyle.Default,
    val body: TextStyle = TextStyle.Default,
    val bodyBold: TextStyle = TextStyle.Default,
    val button: TextStyle = TextStyle.Default,
)

val LocalAppColors = staticCompositionLocalOf { AppColors() }
val LocalAppTypography = staticCompositionLocalOf { AppTypography() }

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalAppColors provides if (darkTheme) DarkColors else LightColors,
        LocalAppTypography provides Typography,
        content = content
    )
}


object AppTheme {
    val color: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current
    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

}
