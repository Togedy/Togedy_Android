package com.example.togedy_android.presentation.planner.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.togedy_android.core.design_system.component.BorderTextField
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import com.example.togedy_android.util.noRippleClickable
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.TogedyButtonBasic
import com.example.togedy_android.domain.model.planner.StudyTagItem
import com.example.togedy_android.util.toColor

@Composable
fun StudyTagDialog(
    title: String,
    studyTagItem: StudyTagItem = StudyTagItem(name = "", color = ""),
    onDismissRequest: () -> Unit,
    onSubmitButtonClicked: (StudyTagItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val initName = if (studyTagItem.name != "") studyTagItem.name else ""
    val initColor = if (studyTagItem.color != "") studyTagItem.color.toColor() else null
    var subjectName by remember { mutableStateOf(initName) }
    var selectedColor by remember { mutableStateOf<Color?>(initColor) }

    val colorMap = mapOf(
        TogedyTheme.colors.color3 to "color3",
        TogedyTheme.colors.color4 to "color4",
        TogedyTheme.colors.color5 to "color5",
        TogedyTheme.colors.color6 to "color6",
        TogedyTheme.colors.color7 to "color7",
        TogedyTheme.colors.color8 to "color8",
        TogedyTheme.colors.color9 to "color9",
        TogedyTheme.colors.color10 to "color10",
        TogedyTheme.colors.color11 to "color11",
        TogedyTheme.colors.color12 to "color12",
        TogedyTheme.colors.color13 to "color13",
    )

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

            BorderTextField(
                title = "태그이름",
                value = subjectName,
                onTextFieldChange = { subjectName = it },
                titleTextStyle = TogedyTheme.typography.body3B,
                textStyle = TogedyTheme.typography.body1M
            )

            Spacer(Modifier.height(26.dp))

            SubjectColorSelection(
                selectedColor = selectedColor,
                onColorSelected = { selectedColor = it }
            )

            Spacer(Modifier.height(26.dp))

            TogedyButtonBasic(
                buttonText = "완료",
                isActivated = subjectName != "" && selectedColor != null,
                onButtonClick = {
                    onSubmitButtonClicked(
                        StudyTagItem(
                            id = studyTagItem.id,
                            name = subjectName,
                            color = colorMap[selectedColor] ?: "color1"
                        )
                    )
                }
            )
        }
    }
}

@Composable
fun SubjectColorSelection(
    selectedColor: Color? = null,
    onColorSelected: (Color) -> Unit,
) {
    val colorList = listOf(
        TogedyTheme.colors.color3,
        TogedyTheme.colors.color4,
        TogedyTheme.colors.color5,
        TogedyTheme.colors.color6,
        TogedyTheme.colors.color7,
        TogedyTheme.colors.color8,
        TogedyTheme.colors.color8,
        TogedyTheme.colors.color9,
        TogedyTheme.colors.color10,
        TogedyTheme.colors.color11,
        TogedyTheme.colors.color12,
        TogedyTheme.colors.color13,
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "색상",
            style = TogedyTheme.typography.body3B,
            color = TogedyTheme.colors.gray500,
            textAlign = TextAlign.Start
        )

        Spacer(Modifier.height(6.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            horizontalArrangement = Arrangement.spacedBy(28.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(colorList) { color ->
                ColorBlock(
                    color = color,
                    selectedColor = selectedColor,
                    onColorClicked = {
                        onColorSelected(it)
                    }
                )
            }
        }
    }
}

@Composable
fun ColorBlock(
    color: Color,
    selectedColor: Color? = null,
    onColorClicked: (Color) -> Unit,
) {
    Box(
        modifier = Modifier
            .size(22.dp)
            .clip(CircleShape)
            .background(color)
            .noRippleClickable { onColorClicked(color) }
    ) {
        if (selectedColor == color) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_check_circle),
                contentDescription = "선택됨",
                tint = Color.Black,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(22.dp)
            )
        }
    }
}

@Preview
@Composable
fun SubjectDialogPreview() {
    StudyTagDialog(
        title = "과목설정",
        onDismissRequest = { },
        onSubmitButtonClicked = { }
    )
}