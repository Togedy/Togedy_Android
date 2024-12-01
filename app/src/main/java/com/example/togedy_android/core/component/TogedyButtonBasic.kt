package com.example.togedy_android.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.ui.theme.TogedyTheme

@Composable
fun TogedyButtonBasic(
    buttonText: String,
    isActivated: Boolean,
) {
    val buttonColor = if (isActivated) TogedyTheme.colors.yellowMain else TogedyTheme.colors.gray200
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(buttonColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buttonText,
            style = TogedyTheme.typography.body2B,
            color = TogedyTheme.colors.white
        )
    }
}

@Preview
@Composable
fun TogedyButtonBasicPreview(modifier: Modifier = Modifier) {
    TogedyButtonBasic("완료", false)
}