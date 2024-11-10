package com.example.togedy_android.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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