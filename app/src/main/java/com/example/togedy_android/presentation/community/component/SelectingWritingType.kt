package com.example.togedy_android.presentation.community.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.domain.type.WritingType
import com.example.togedy_android.core.design_system.theme.TogedyTheme

@Composable
fun SelectingWritingType(
    writingType: WritingType = WritingType.BULLETIN_BOARD,
    onSectionClicked: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "게시판",
            style = TogedyTheme.typography.body2B,
            color = TogedyTheme.colors.gray500,
        )
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(30.dp)
                .border(1.dp, TogedyTheme.colors.gray200, shape = RoundedCornerShape(5.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp)
                .clickable { onSectionClicked() },
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = writingType.type,
                    style = TogedyTheme.typography.body2M
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_chevron_selector_vertical),
                    contentDescription = "선택 버튼",
                    tint = TogedyTheme.colors.gray700
                )
            }
        }
    }
}

@Preview
@Composable
fun SelectingWritingTypePreview() {
    SelectingWritingType(
        onSectionClicked = { }
    )
}