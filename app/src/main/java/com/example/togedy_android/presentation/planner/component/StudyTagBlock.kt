package com.example.togedy_android.presentation.planner.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.domain.model.planner.StudyTagItem
import com.example.togedy_android.util.noRippleClickable
import com.example.togedy_android.util.toColor

@Composable
fun StudyTagBlock(
    studyTagItem: StudyTagItem,
    selectedStudyTagId: Int = -2,
    onTagClicked: () -> Unit = { },
    onTagSelected: (Int) -> Unit = { },
) {
    val isSelected = studyTagItem.id == selectedStudyTagId

    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = studyTagItem.color.toColor() ?: TogedyTheme.colors.gray200,
                shape = CircleShape
            )
            .clip(CircleShape)
            .background(
                color = if (isSelected) studyTagItem.color.toColor()
                    ?: TogedyTheme.colors.gray200 else TogedyTheme.colors.white
            )
            .noRippleClickable {
                onTagClicked()
                onTagSelected(studyTagItem.id)
            }
            .padding(6.dp)
            .padding(end = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(
                    color = if (isSelected) TogedyTheme.colors.white else studyTagItem.color.toColor()
                        ?: TogedyTheme.colors.gray200
                )
        )

        Spacer(Modifier.width(6.dp))

        Text(
            text = studyTagItem.name,
            style = TogedyTheme.typography.body1M,
            color = TogedyTheme.colors.gray600
        )
    }
}

@Preview
@Composable
fun StudyTagPreview() {
    StudyTagBlock(
        StudyTagItem(name = "국어", color = "color14")
    )
}