package com.example.togedy_android.presentation.community

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.PhotoInputSection
import com.example.togedy_android.core.design_system.component.TogedyButtonBasic
import com.example.togedy_android.core.design_system.component.TopBarBasic
import com.example.togedy_android.core.design_system.component.WritingContentTextField
import com.example.togedy_android.core.design_system.component.WritingTitleField
import com.example.togedy_android.presentation.community.component.SelectingWritingType
import com.example.togedy_android.core.design_system.theme.TogedyTheme

@Composable
fun AddWriting(
    viewModel: WritingViewModel = viewModel(),
    closeButtonClicked: () -> Unit,
) {
    val title = viewModel.title.collectAsStateWithLifecycle()
    val showPhotoError = viewModel.showPhotoError.collectAsStateWithLifecycle()
    val content = viewModel.content.collectAsStateWithLifecycle()
    val isActivated = viewModel.isActivated.collectAsStateWithLifecycle()

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
            value = title.value,
            onTextFieldChange = {
                viewModel.updateTitle(it)
                viewModel.updateIsActivated()
            },
            placeholder = "제목을 입력해주세요"
        )

        Spacer(modifier = Modifier.height(22.dp))
        PhotoInputSection(
            photoList = viewModel.photoItems,
            onAddImageButtonClicked = {
                //갤러리 오픈
                val result = viewModel.addPhotoItems(R.drawable.ic_image_plus)
                viewModel.updateShowPhotoError(!result)
            },
            onDeleteBtnClicked = {
                viewModel.deletePhotoItems(it)
            }
        )
        if (showPhotoError.value) {
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

        val contentTopPadding = if (showPhotoError.value) 16.dp else 38.dp
        Spacer(modifier = Modifier.height(contentTopPadding))
        WritingContentTextField(
            value = content.value,
            onTextFieldChange = {
                viewModel.updateContent(it)
                viewModel.updateIsActivated()
            },
            placeholder = "내용을 입력해주세요"
        )

        Spacer(modifier = Modifier.weight(1f))
        TogedyButtonBasic(
            buttonText = "등록하기",
            isActivated = isActivated.value
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Preview
@Composable
fun AddWritingPreview() {
    AddWriting(
        closeButtonClicked = { }
    )
}