package com.example.togedy_android.core.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.togedy_android.ui.theme.TogedyTheme

@Composable
fun TogedyTitle(
    text: String
) {
    Text(
        text = text,
        style = TogedyTheme.typography.body1B,
        color = TogedyTheme.colors.black
    )
}