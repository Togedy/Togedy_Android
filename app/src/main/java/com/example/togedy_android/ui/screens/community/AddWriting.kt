package com.example.togedy_android.ui.screens.community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.ui.component.TopBarBasic
import com.example.togedy_android.ui.component.user_input.WritingTitleField
import com.example.togedy_android.ui.component.community.SelectingWritingType
import com.example.togedy_android.ui.theme.TogedyTheme

@Composable
fun AddWriting(
    closeButtonClicked: () -> Unit,
) {
    var title by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TogedyTheme.colors.white)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        TopBarBasic(
            leftButtonIcon = R.drawable.ic_x_close,
            title = "글 등록하기",
            onLeftButtonClicked = { closeButtonClicked() }
        )
        Spacer(modifier = Modifier.height(24.dp))
        SelectingWritingType(
            onSectionClicked = { }
        )

        Spacer(modifier = Modifier.height(27.dp))
        WritingTitleField(
            value = title,
            onTextFieldChange = { title = it },
            placeholder = "제목을 입력해주세요"
        )

        Spacer(modifier = Modifier.height(22.dp))

    }
}


@Preview
@Composable
fun AddWritingPreview(modifier: Modifier = Modifier) {
    AddWriting(
        closeButtonClicked = { }
    )
}