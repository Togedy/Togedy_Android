package com.example.togedy_android.core.design_system.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.togedy_android.R

val PretendardBold = FontFamily(Font(R.font.pretendard_bold))
val PretendardMedium = FontFamily(Font(R.font.pretendard_medium))
val PretendardRegular = FontFamily(Font(R.font.pretendard_regular))

@Immutable
data class TogedyTypography(
    val headline1B: TextStyle,
    val headline2R: TextStyle,
    val headline2B: TextStyle,
    val headline3R: TextStyle,
    val headline3M: TextStyle,
    val headline3B: TextStyle,
    val body1R: TextStyle,
    val body1M: TextStyle,
    val body1B: TextStyle,
    val body2R: TextStyle,
    val body2M: TextStyle,
    val body2B: TextStyle,
    val body3M: TextStyle,
    val body3B: TextStyle,
    val caption: TextStyle,
    val overline: TextStyle,
)

val defaultTogedyTypography = TogedyTypography(
    // Title
    headline1B = TextStyle(
        fontFamily = PretendardBold,
        fontSize = 26.sp,
        lineHeight = 36.sp
    ),
    headline2R = TextStyle(
        fontFamily = PretendardRegular,
        fontSize = 20.sp,
        lineHeight = 26.sp
    ),
    headline2B = TextStyle(
        fontFamily = PretendardBold,
        fontSize = 20.sp,
        lineHeight = 26.sp
    ),
    headline3R = TextStyle(
        fontFamily = PretendardRegular,
        fontSize = 18.sp,
        lineHeight = 22.sp
    ),
    headline3M = TextStyle(
        fontFamily = PretendardMedium,
        fontSize = 18.sp,
        lineHeight = 22.sp
    ),
    headline3B = TextStyle(
        fontFamily = PretendardBold,
        fontSize = 18.sp,
        lineHeight = 22.sp
    ),
    body1R = TextStyle(
        fontFamily = PretendardRegular,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),
    body1M = TextStyle(
        fontFamily = PretendardMedium,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),
    body1B = TextStyle(
        fontFamily = PretendardBold,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),
    body2R = TextStyle(
        fontFamily = PretendardRegular,
        fontSize = 14.sp,
        lineHeight = 18.sp
    ),
    body2M = TextStyle(
        fontFamily = PretendardMedium,
        fontSize = 14.sp,
        lineHeight = 18.sp
    ),
    body2B = TextStyle(
        fontFamily = PretendardBold,
        fontSize = 14.sp,
        lineHeight = 18.sp
    ),
    body3M = TextStyle(
        fontFamily = PretendardMedium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    body3B = TextStyle(
        fontFamily = PretendardBold,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    caption = TextStyle(
        fontFamily = PretendardRegular,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    overline = TextStyle(
        fontFamily = PretendardRegular,
        fontSize = 10.sp,
        lineHeight = 12.sp
    )
)

val LocalTogedyTypography = staticCompositionLocalOf { defaultTogedyTypography }
