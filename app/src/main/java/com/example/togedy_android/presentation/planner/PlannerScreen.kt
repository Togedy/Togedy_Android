package com.example.togedy_android.presentation.planner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.core.design_system.theme.Togedy_AndroidTheme
import com.example.togedy_android.presentation.planner.component.PlannerHomeTopBar

@Composable
fun PlannerScreen(
    modifier: Modifier = Modifier,
    onSettingButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = TogedyTheme.colors.white)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(20.dp))

        PlannerHomeTopBar(
            onSettingButtonClick = onSettingButtonClick
        )
    }
}

@Preview
@Composable
fun PlannerScreenPreview() {
    Togedy_AndroidTheme {
        PlannerScreen(
            onSettingButtonClick = { }
        )
    }
}