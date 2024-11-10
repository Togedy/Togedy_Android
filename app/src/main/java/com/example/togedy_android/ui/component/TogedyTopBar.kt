package com.example.togedy_android.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.togedy_android.R
import com.example.togedy_android.ui.theme.TogedyTheme

@Composable
fun TopBarWithTextBtn(
    leftButtonIcon: Int,
    title: String,
    buttonText: String,
    buttonActive: Boolean,
    onLeftButtonClicked: () -> Unit,
    onRightButtonClicked: () -> Unit
) {
    val color = if (buttonActive) TogedyTheme.colors.yellowMain else TogedyTheme.colors.gray400

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(leftButtonIcon),
            contentDescription = "닫기 버튼",
            modifier = Modifier.clickable { onLeftButtonClicked() }
        )

        Text(
            text = title,
            style = TogedyTheme.typography.headline2B,
            color = TogedyTheme.colors.black
        )

        Text(
            text = buttonText,
            style = TogedyTheme.typography.headline3B,
            color = color,
            modifier = Modifier.clickable { onRightButtonClicked() }
        )
    }
}

@Preview
@Composable
fun TogedyTopBarPreview(modifier: Modifier = Modifier) {
    TopBarWithTextBtn(
        leftButtonIcon = R.drawable.ic_x_close,
        title = "일정 추가하기",
        buttonText = "추가",
        buttonActive = false,
        onLeftButtonClicked = {},
        onRightButtonClicked = {}
    )
}