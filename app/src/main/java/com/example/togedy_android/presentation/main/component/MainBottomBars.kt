package com.example.togedy_android.presentation.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.presentation.main.MainTab
import com.example.togedy_android.util.noRippleClickable

@Composable
fun MainBottomBars(
    onTabSelect: (MainTab) -> Unit,
    selectedTab: MainTab?,
    tabs: List<MainTab>,
    visibility: Boolean,
    modifier: Modifier = Modifier,
    containerColor: Color = TogedyTheme.colors.white,
    selectedColor: Color = TogedyTheme.colors.yellowMain,
    unselectedColor: Color = TogedyTheme.colors.gray400,
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .background(color = containerColor)
            .padding(start = 37.dp, end = 37.dp, top = 8.dp, bottom = 31.dp),
//            .showIf(visibility),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        tabs.forEach { tab ->
            val selected = tab == selectedTab
            val color = if (selected) selectedColor else unselectedColor
            Column(
                modifier = Modifier.noRippleClickable { onTabSelect(tab) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = tab.iconRes),
                    contentDescription = stringResource(id = tab.title),
                    tint = color
                )
            }
        }
    }
}