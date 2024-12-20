package com.example.togedy_android.presentation.calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.util.noRippleClickable
import com.example.togedy_android.core.design_system.theme.TogedyTheme

@Composable
fun CalendarFloatingBtn(
    onButtonClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(color = colorResource(R.color.yellow_main))
            .padding(14.dp)
            .noRippleClickable { onButtonClicked() }
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_calendar_plus),
            contentDescription = "일정 추가 버튼",
            tint = TogedyTheme.colors.white,
        )
    }
}

@Composable
fun CalendarAddButton(
    imageVector: Int,
    description: String,
    onButtonClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .noRippleClickable { onButtonClicked() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = description,
            style = TogedyTheme.typography.body2M,
            color = TogedyTheme.colors.white
        )
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(color = colorResource(R.color.white))
                .padding(14.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(imageVector),
                contentDescription = description,
                tint = TogedyTheme.colors.yellowMain,
            )
        }
    }
}