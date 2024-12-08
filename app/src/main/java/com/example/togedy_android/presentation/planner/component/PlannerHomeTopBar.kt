package com.example.togedy_android.presentation.planner.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.core.design_system.theme.Togedy_AndroidTheme
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.util.noRippleClickable

@Composable
fun PlannerHomeTopBar(
    onSettingButtonClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.planner_home_title),
            style = TogedyTheme.typography.headline3B,
            color = TogedyTheme.colors.black
        )

        Spacer(Modifier.weight(1f))

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_planner_settings),
            contentDescription = stringResource(R.string.btn_settings_description),
            tint = TogedyTheme.colors.gray600,
            modifier = Modifier
                .size(24.dp)
                .noRippleClickable(onClick = onSettingButtonClick)
        )
    }
}

@Preview
@Composable
fun PlannerHomeTopBarPreview() {
    Togedy_AndroidTheme {
        PlannerHomeTopBar(
            onSettingButtonClick = { }
        )
    }
}