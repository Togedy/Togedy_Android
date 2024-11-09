package com.example.togedy_android.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.util.copy
import com.example.togedy_android.ui.theme.TogedyTheme

@Composable
fun BorderTextField(
    title: String,
    value: String,
    onTextFieldChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TogedyTheme.typography.body1M,
    titleTextStyle: TextStyle = TogedyTheme.typography.body2B,
    focusColor: Color = TogedyTheme.colors.yellowMain,
    unfocusColor: Color = TogedyTheme.colors.gray500,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isSingleLine: Boolean = true,
) {
    var onFocus by remember { mutableStateOf(false) }
    val borderColor = if (onFocus) focusColor else unfocusColor

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = titleTextStyle,
            color = borderColor,
        )
        Spacer(modifier = Modifier.height(4.dp))

        BasicTextField(
            value = value,
            onValueChange = onTextFieldChange,
            textStyle = textStyle.copy(
                color = borderColor,
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(10.dp)
                .onFocusEvent { onFocus = it.isFocused },
            visualTransformation = visualTransformation,
            singleLine = isSingleLine,
        )
    }
}

@Preview
@Composable
fun BorderTextFieldPreview(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    BorderTextField(
        title = "title",
        value = text,
        onTextFieldChange = { text = it },
    )
}