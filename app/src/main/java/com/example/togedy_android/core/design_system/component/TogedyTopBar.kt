package com.example.togedy_android.core.design_system.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.util.noRippleClickable

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
            contentDescription = stringResource(R.string.btn_close_description),
            modifier = Modifier.noRippleClickable { onLeftButtonClicked() }
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
            modifier = Modifier.noRippleClickable { onRightButtonClicked() }
        )
    }
}

@Composable
fun TopBarBasic(
    leftButtonIcon: Int,
    title: String,
    onLeftButtonClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Icon(
                imageVector = ImageVector.vectorResource(leftButtonIcon),
                contentDescription = stringResource(R.string.btn_close_description),
                modifier = Modifier.noRippleClickable { onLeftButtonClicked() }
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = title,
                style = TogedyTheme.typography.headline2B,
                color = TogedyTheme.colors.black
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
fun TogedyTopBarPreview() {
    TopBarWithTextBtn(
        leftButtonIcon = R.drawable.ic_x_close,
        title = "일정 추가하기",
        buttonText = "추가",
        buttonActive = false,
        onLeftButtonClicked = {},
        onRightButtonClicked = {}
    )
}

@Preview
@Composable
fun TopBarBasicPreview() {
    TopBarBasic(
        leftButtonIcon = R.drawable.ic_x_close,
        title = "글 등록하기",
        onLeftButtonClicked = { }
    )
}