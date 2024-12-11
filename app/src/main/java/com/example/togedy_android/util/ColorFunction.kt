package com.example.togedy_android.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.togedy_android.core.design_system.theme.TogedyTheme

/**
 * string값으로 넘어온 색상을 실제 색상값으로 변경해주는 함수
 */
@Composable
fun String.toColor(): Color? {
    val colorMap = mapOf(
        "color1" to TogedyTheme.colors.color1,
        "color2" to TogedyTheme.colors.color2,
        "color3" to TogedyTheme.colors.color3,
        "color4" to TogedyTheme.colors.color4,
        "color5" to TogedyTheme.colors.color5,
        "color6" to TogedyTheme.colors.color6,
        "color7" to TogedyTheme.colors.color7,
        "color8" to TogedyTheme.colors.color8,
        "color9" to TogedyTheme.colors.color9,
        "color10" to TogedyTheme.colors.color10,
        "color11" to TogedyTheme.colors.color11,
        "color12" to TogedyTheme.colors.color12,
        "color13" to TogedyTheme.colors.color13,
        "color14" to TogedyTheme.colors.color14,
        "color15" to TogedyTheme.colors.color15
    )
    return colorMap[this]
}
