package com.example.togedy_android.core.component

import com.example.togedy_android.presentation.ui.component.category.drawDashedBorder
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.ui.theme.TogedyTheme

@Composable
fun PhotoInputSection(
    photoList: List<Int>,
    onAddImageButtonClicked: () -> Unit,
    onDeleteBtnClicked: (Int) -> Unit
) {
    val photoItems = listOf(-1) + photoList
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            Text(
                text = "사진  ( ${photoList.size} / 3 )",
                style = TogedyTheme.typography.body3B,
                color = TogedyTheme.colors.gray700
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(photoItems.size) { index ->
                if (index == 0) {
                    AddImageButton(
                        onAddImageButtonClicked = onAddImageButtonClicked
                    )
                } else {
                    ImageItem(
                        imageFile = photoItems[index],
                        onDeleteBtnClicked = { onDeleteBtnClicked(index-1) }
                    )
                }
            }
        }
    }
}

@Composable
fun ImageItem(
    imageFile: Int,
    onDeleteBtnClicked: () -> Unit,
) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.TopEnd
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clickable { onDeleteBtnClicked() }
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xCC4C4C4C)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_trash_can),
                contentDescription = stringResource(R.string.btn_delete_description),
                modifier = Modifier.size(16.dp),
                tint = TogedyTheme.colors.white
            )
        }
        Image(
            painter = painterResource(imageFile),
            contentDescription = stringResource(R.string.image),
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
fun AddImageButton(
    onAddImageButtonClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFF6F6F6))
            .drawWithContent {
                drawContent()
                drawDashedBorder(strokeWidth = 2.dp.toPx(), dashLength = 4.dp.toPx())
            }
            .clickable { onAddImageButtonClicked() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_image_plus),
                contentDescription = stringResource(R.string.btn_add_image_description),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.add_image),
                style = TogedyTheme.typography.overline,
                color = TogedyTheme.colors.gray300
            )
        }
    }
}

@Preview
@Composable
fun PhotoInputSectionPreview() {
    val photoList = listOf(R.drawable.ic_building, R.drawable.ic_building, R.drawable.ic_building)
    PhotoInputSection(
        photoList = photoList,
        onAddImageButtonClicked = { },
        onDeleteBtnClicked = { }
    )
}