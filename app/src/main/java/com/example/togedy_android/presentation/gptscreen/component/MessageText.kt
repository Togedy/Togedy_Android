package com.example.togedy_android.presentation.gptscreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.togedy_android.core.design_system.theme.TogedyTheme

@Composable
fun MessageText(
    text: String,
    isSent: Boolean,
    backgroundColor: Color = if (isSent) TogedyTheme.colors.gray100 else TogedyTheme.colors.yellow500,
    textColor: Color = if (isSent) TogedyTheme.colors.gray900 else TogedyTheme.colors.white,
    alignment: Alignment.Horizontal = if (isSent) Alignment.End else Alignment.Start
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(alignment)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 14.dp, vertical = 10.dp),
            color = textColor
        )
    }
}
