package com.example.togedy_android.core.design_system.component

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.togedy_android.core.design_system.theme.TogedyTheme

/**
 * 회색 가로 선 컴포넌트입니다.
 *
 * @param thickness 선 두께를 나타내는 dp값 (default 1.dp)
 * @param lineColor 선 색상을 나타내는 값 (default gray100)
 */
@Composable
fun GrayLine(
    thickness: Dp = 1.dp,
    lineColor: Color = TogedyTheme.colors.gray100
) {
    HorizontalDivider(
        thickness = thickness,
        color = lineColor
    )
}

@Preview
@Composable
fun GrayLinePreview(modifier: Modifier = Modifier) {
    GrayLine()
}