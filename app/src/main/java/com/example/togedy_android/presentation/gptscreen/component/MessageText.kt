package com.example.togedy_android.presentation.gptscreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import java.time.LocalDateTime
import kotlin.toString

@Composable
fun MessageText(
    text: String,
    isSent: Boolean,
    backgroundColor: Color = if (isSent) TogedyTheme.colors.gray100 else TogedyTheme.colors.yellow500,
    textColor: Color = if (isSent) TogedyTheme.colors.gray900 else TogedyTheme.colors.white,
    alignment: Alignment.Horizontal = if (isSent) Alignment.End else Alignment.Start,
    modifier: Modifier = if (isSent) Modifier.padding(start = 26.dp) else Modifier.padding(end = 26.dp)
) {
    val time = LocalDateTime.now()

    Column(
        modifier = modifier
            .padding(bottom = 20.dp),
        horizontalAlignment = if (isSent) Alignment.End else Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = text,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentWidth(alignment)
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                color = textColor
            )
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = "${time.hour}:${time.minute}",
            style = TogedyTheme.typography.caption,
            color = TogedyTheme.colors.gray500,
            modifier = Modifier.padding(horizontal = 2.dp)
        )
    }
}
