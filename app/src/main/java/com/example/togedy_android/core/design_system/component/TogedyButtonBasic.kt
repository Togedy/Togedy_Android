package com.example.togedy_android.core.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.util.noRippleClickable

/**
 * 비활성화 시 gray200, 활성화 시 yellowMain으로 바뀌는 버튼 컴포넌트입니다.
 *
 * @param buttonText 버튼에 표시될 내용
 * @param isActivated 버튼 활성화 여부
 * @param onButtonClick 버튼 클릭 시 실행되는 함수
 */
@Composable
fun TogedyButtonBasic(
    buttonText: String,
    isActivated: Boolean,
    onButtonClick: () -> Unit = { },
    onNotActivatedButtonClick: () -> Unit = { },
) {
    val buttonColor = if (isActivated) TogedyTheme.colors.yellowMain else TogedyTheme.colors.gray200
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(buttonColor)
            .noRippleClickable(
                onClick = {
                    if (isActivated) {
                        onButtonClick()
                    } else {
                        onNotActivatedButtonClick()
                    }
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buttonText,
            style = TogedyTheme.typography.body2B,
            color = TogedyTheme.colors.white
        )
    }
}


/**
 * 비활성화 시 회색테두리, 활성화 시 yellowMain으로 바뀌는 버튼 컴포넌트입니다.
 *
 *  @param buttonText 버튼에 표시될 내용
 *  @param isActivated 버튼 활성화 여부
 *  @param modifier 수정자
 *  @param onButtonClick 버튼 클릭 시 실행되는 함수
 */
@Composable
fun TogedyButtonWithBorder(
    buttonText: String,
    isActivated: Boolean,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = { },
) {
    val borderColor = if (isActivated) TogedyTheme.colors.yellow500 else TogedyTheme.colors.gray200
    val buttonColor = if (isActivated) TogedyTheme.colors.yellow500 else TogedyTheme.colors.white
    val textColor = if (isActivated) TogedyTheme.colors.white else TogedyTheme.colors.gray200

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(buttonColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .padding(horizontal = 18.dp, vertical = 7.dp)
    ) {
        Text(
            text = buttonText,
            style = TogedyTheme.typography.body3B,
            color = textColor
        )
    }
}

@Preview
@Composable
fun TogedyButtonBasicPreview() {
    TogedyButtonBasic("완료", false)
}

@Preview
@Composable
fun TogedyButtonWithBorderPreview() {
    TogedyButtonWithBorder(
        "목표 수정하기",
        true,
    )
}