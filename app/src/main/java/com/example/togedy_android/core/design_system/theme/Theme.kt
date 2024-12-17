package com.example.togedy_android.core.design_system.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = yellowMain,
    secondary = gray600,
    tertiary = yellow200
)

private val LightColorScheme = lightColorScheme(
    primary = yellowMain,
    secondary = gray600,
    tertiary = yellow200

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

object TogedyTheme {
    val colors: TogedyColors
        @Composable
        @ReadOnlyComposable
        get() = LocalTogedyColors.current

    val typography: TogedyTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTogedyTypography.current
}

@Composable
fun ProvideDateRoadColorsAndTypography(
    colors: TogedyColors,
    typography: TogedyTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTogedyColors provides colors,
        LocalTogedyTypography provides typography,
        content = content
    )
}

@Composable
fun Togedy_AndroidTheme(
    backgroundColor: Color = defaultTogedyColors.white,
    content: @Composable () -> Unit
) {
    ProvideDateRoadColorsAndTypography(
        colors = defaultTogedyColors,
        typography = defaultTogedyTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.run {
                    statusBarColor = backgroundColor.toArgb()
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = true
                }
            }
        }

        MaterialTheme(
            content = content
        )
    }
}
//@Composable
//fun Togedy_AndroidTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//    val colorScheme = when {
////        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
////            val context = LocalContext.current
////            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
////        }
////
////        darkTheme -> DarkColorScheme
////        else -> LightColorScheme
////    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}