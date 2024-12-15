package com.example.togedy_android.presentation.planner.planInfoDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.togedy_android.core.design_system.component.BorderTextField
import com.example.togedy_android.core.design_system.component.TogedyButtonBasic
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.domain.model.planner.PlanItem
import com.example.togedy_android.domain.type.PlanState
import com.example.togedy_android.presentation.planner.component.StudyTagBlock
import com.example.togedy_android.util.toColor

@Composable
fun PlanInfoDialog(
    title: String,
    planItem: PlanItem = PlanItem(todoID = -1, title = "", subjectColor = "", status = PlanState.NOT_STARTED.state),
    onDismissRequest: () -> Unit,
    onSubmitButtonClicked: (PlanItem) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlanInfoDialogViewModel = hiltViewModel(),
) {
    val initTitle = if (planItem.title != "") planItem.title else ""
    val initColor = if (planItem.subjectColor != "") planItem.subjectColor.toColor() else null
    var planTitle by remember { mutableStateOf(initTitle) }
    var selectedColor by remember { mutableStateOf<Color?>(initColor) }
    var selectedStudyTagId by remember { mutableIntStateOf(-1) }
    val studyTagList = viewModel.studyTagList.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.getStudyTagList()
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = modifier
                .width(335.dp)
                .wrapContentHeight()
                .background(
                    color = TogedyTheme.colors.white,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(20.dp)
                .padding(horizontal = 2.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = TogedyTheme.typography.body1B,
                color = TogedyTheme.colors.black
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "공부 태그",
                style = TogedyTheme.typography.body3B,
                color = TogedyTheme.colors.gray500,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(6.dp))

            if (studyTagList.isEmpty()) {
                Text(
                    text = "공부 태그가 없습니다. 공부 태그를 먼저 추가해주세요!",
                    style = TogedyTheme.typography.body2M,
                    color = TogedyTheme.colors.gray200
                )
            } else {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(studyTagList) { studyTag ->
                        StudyTagBlock(
                            studyTagItem = studyTag,
                            selectedStudyTagId = selectedStudyTagId,
                            onTagSelected = { selectedStudyTagId = it }
                        )
                    }
                }
            }

            Spacer(Modifier.height(28.dp))


            BorderTextField(
                title = "공부 내용",
                value = planTitle,
                onTextFieldChange = { planTitle = it },
                titleTextStyle = TogedyTheme.typography.body3B,
                textStyle = TogedyTheme.typography.body1M
            )

            Spacer(Modifier.height(26.dp))

            TogedyButtonBasic(
                buttonText = "완료",
                isActivated = planTitle != "" && selectedColor != null,
                onButtonClick = {
                    onSubmitButtonClicked(
                        PlanItem(
                            todoID = planItem.todoID,
                            subjectColor = selectedColor.toString(),
                            subjectId = selectedStudyTagId,
                            title = planTitle,
                            status = planItem.status
                        )
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun PlanInfoDialogPreview() {
    PlanInfoDialog(
        title = "플랜 상세정보",
        onDismissRequest = { },
        onSubmitButtonClicked = { }
    )
}