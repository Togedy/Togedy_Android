package com.example.togedy_android.ui.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.ui.theme.TogedyTheme

@Composable
fun AddScheduleBtn(
    onButtonClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(color = colorResource(R.color.yellow_main))
            .padding(14.dp)
            .clickable { onButtonClicked() }
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_calendar_plus),
            contentDescription = "일정 추가 버튼",
            tint = TogedyTheme.colors.white
        )
    }
}