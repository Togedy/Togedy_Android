package com.example.togedy_android.ui.screens.community

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.ui.component.TogedyButtonBasic
import com.example.togedy_android.ui.component.TopBarBasic
import com.example.togedy_android.ui.component.user_input.WritingTitleField
import com.example.togedy_android.ui.component.community.SelectingWritingType
import com.example.togedy_android.ui.component.user_input.PhotoInputSection
import com.example.togedy_android.ui.component.user_input.WritingContentTextField
import com.example.togedy_android.ui.theme.TogedyTheme

@Composable
fun AddWriting(
    closeButtonClicked: () -> Unit,
) {
    val writingViewModel = WritingViewModel()
    var title by remember { mutableStateOf("") }
    var showPhotoError by remember { mutableStateOf(false) }
    var content by remember { mutableStateOf("") }
    var isActivated by remember { mutableStateOf(false) }

    fun updateIsActivated() {
        isActivated = title.isNotBlank() && content.isNotBlank()
    }

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
            onTextFieldChange = {
                title = it
                updateIsActivated()
            },
            placeholder = "제목을 입력해주세요"
        )

        Spacer(modifier = Modifier.height(22.dp))
        PhotoInputSection(
            photoList = writingViewModel.photoItems,
            onAddImageButtonClicked = {
                //갤러리 오픈
                val result = writingViewModel.addPhotoItems(R.drawable.ic_image_plus)
                if (!result) {
                    showPhotoError = true
                } else {
                    showPhotoError = false
                }
            },
            onDeleteBtnClicked = {
                writingViewModel.deletePhotoItems(it)
            }
        )
        if (showPhotoError) {
            Row(
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_alert_triangle),
                    contentDescription = "경고 아이콘",
                    modifier = Modifier.size(16.dp),
                    tint = TogedyTheme.colors.red100
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "올릴 수 있는 사진의 개수는 최대 3장입니다.",
                    style = TogedyTheme.typography.body3M,
                    color = TogedyTheme.colors.red100
                )
            }
        }

        val contentTopPadding = if (showPhotoError) 16.dp else 38.dp
        Spacer(modifier = Modifier.height(contentTopPadding))
        WritingContentTextField(
            value = content,
            onTextFieldChange = {
                content = it
                updateIsActivated()
            },
            placeholder = "내용을 입력해주세요"
        )

        Spacer(modifier = Modifier.weight(1f))
        TogedyButtonBasic(
            buttonText = "등록하기",
            isActivated = isActivated
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Preview
@Composable
fun AddWritingPreview(modifier: Modifier = Modifier) {
    AddWriting(
        closeButtonClicked = { }
    )
}