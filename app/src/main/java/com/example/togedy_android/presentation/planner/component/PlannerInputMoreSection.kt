package com.example.togedy_android.presentation.planner.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.util.noRippleClickable
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.GrayLine
import com.example.togedy_android.core.design_system.theme.TogedyTheme

@Composable
fun PlannerInputMoreSection(
    onMoreButtonClicked: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.planner_input_plan_more),
            style = TogedyTheme.typography.body3B,
            color = TogedyTheme.colors.darkblue200,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClickable(onMoreButtonClicked)
        )

        Spacer(Modifier.height(10.dp))

        GrayLine()
        Spacer(Modifier.height(8.dp))
        GrayLine()
    }
}

@Preview
@Composable
fun PlannerInputMoreSectionPreview() {
    PlannerInputMoreSection(
        onMoreButtonClicked = { }
    )
}