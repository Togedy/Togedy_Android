package com.example.togedy_android.core.design_system.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.util.noRippleClickable
import java.time.LocalDate

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

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            BasicTextField(
                value = value,
                onValueChange = onTextFieldChange,
                textStyle = textStyle.copy(
                    color = TogedyTheme.colors.black,
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
            if (onFocus && value.isNotEmpty()) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_x_circle),
                    contentDescription = stringResource(R.string.btn_delete_content_all_description),
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable {
                            onTextFieldChange("")
                        }
                )
            }
        }
    }
}

@Composable
fun BorderDateInput(
    startDate: LocalDate?,
    endDate: LocalDate?,
    isSingleDate: Boolean,
    modifier: Modifier = Modifier,
    onIsSingleDateClicked: () -> Unit,
    openCalendarDialog: () -> Unit,
) {
    var onFocus by remember { mutableStateOf(false) }
    val borderColor = if (onFocus) TogedyTheme.colors.yellowMain else TogedyTheme.colors.gray500
    val calendarIconColor =
        if (startDate == null) TogedyTheme.colors.gray300 else TogedyTheme.colors.black
    val isSingleDateTextColor =
        if (isSingleDate) TogedyTheme.colors.gray300 else TogedyTheme.colors.yellowMain
    val isSingleDateIcon =
        if (isSingleDate) R.drawable.ic_circle_btn else R.drawable.ic_check_circle_btn


    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row {
            Text(
                text = stringResource(R.string.calendar_select_date),
                style = TogedyTheme.typography.body2B,
                color = borderColor,
            )
            Spacer(modifier = Modifier.width(14.dp))
            Row(
                modifier = Modifier.noRippleClickable {
                    onIsSingleDateClicked()
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(isSingleDateIcon),
                    contentDescription = stringResource(R.string.calendar_btn_select_period_description),
                    tint = isSingleDateTextColor
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.calendar_period),
                    style = TogedyTheme.typography.body2M,
                    color = isSingleDateTextColor
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(10.dp)
                .onFocusEvent {
                    onFocus = it.isFocused
                    openCalendarDialog()
                },
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_calendar),
                    contentDescription = stringResource(R.string.calendar_btn_calendar_description),
                    tint = calendarIconColor
                )
                Spacer(modifier = Modifier.width(10.dp))
                if (startDate != null) {
                    Text(
                        text = startDate.toString(),
                        style = TogedyTheme.typography.body1M
                    )
                }
                if (endDate != null) {
                    Text(
                        text = " - $endDate",
                        style = TogedyTheme.typography.body1M
                    )
                }
            }
        }
    }
}

@Composable
fun WritingTitleField( //게시글 제목 텍스트 필드
    value: String,
    onTextFieldChange: (String) -> Unit,
    isSingleLine: Boolean = true,
    placeholder: String,
) {
    Column {
        BasicTextField(
            value = value,
            onValueChange = onTextFieldChange,
            textStyle = TogedyTheme.typography.headline3B,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            singleLine = isSingleLine,
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = TogedyTheme.colors.gray100,
                        style = TogedyTheme.typography.headline3B
                    )
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = TogedyTheme.colors.gray200
        )
    }
}


@Composable
fun WritingContentTextField(
    value: String,
    onTextFieldChange: (String) -> Unit,
    placeholder: String,
) {
    BasicTextField(
        value = value,
        onValueChange = onTextFieldChange,
        textStyle = TogedyTheme.typography.body1M,
        modifier = Modifier.fillMaxWidth(),
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    color = TogedyTheme.colors.gray100,
                    style = TogedyTheme.typography.body1M
                )
            }
            innerTextField()
        }
    )
}


@Preview
@Composable
fun BorderTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    BorderTextField(
        title = "title",
        value = text,
        onTextFieldChange = { text = it },
    )
}

@Preview
@Composable
fun BorderDateInputPreview() {
    BorderDateInput(
        startDate = LocalDate.now(),
        endDate = LocalDate.now(),
        isSingleDate = true,
        onIsSingleDateClicked = { },
        openCalendarDialog = { }
    )
}

@Preview
@Composable
fun WritingTitleFieldPreview() {
    var title by remember { mutableStateOf("") }
    WritingTitleField(
        value = title,
        onTextFieldChange = { title = it },
        placeholder = "제목을 입력해주세요"
    )
}

@Preview
@Composable
fun WritingContentTextFieldPreview() {
    var content by remember { mutableStateOf("") }
    WritingContentTextField(
        value = content,
        onTextFieldChange = { content = it },
        placeholder = "내용을 입력해주세요"
    )
}