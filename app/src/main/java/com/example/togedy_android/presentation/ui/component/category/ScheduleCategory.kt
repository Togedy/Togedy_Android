package com.example.togedy_android.presentation.ui.component.category

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.presentation.calendar.component.CalendarCategoryViewModel
import com.example.togedy_android.core.design_system.theme.TogedyTheme

@Composable
fun CategorySelection(
    categories: List<String>,
    onAddButtonClicked: () -> Unit
) {
    val columns = 4
    val items = listOf("Add") + categories
    var isSelected by remember { mutableIntStateOf(-1) }

    Column {
        Text(
            text = "카테고리",
            style = TogedyTheme.typography.body2B,
            color = TogedyTheme.colors.gray500,
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(items.size) { index ->
                if (index == 0) {
                    AddButton(
                        onAddButtonClicked = { onAddButtonClicked() }
                    )
                } else {
                    ScheduleCategoryItem(
                        name = items[index],
                        isSelected = index == isSelected,
                        onCategoryItemSelected = { isSelected = index }
                    )
                }
            }
        }
    }
}

@Composable
fun AddButton(
    onAddButtonClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .drawWithContent {
                drawContent()
                drawDashedBorder(strokeWidth = 2.dp.toPx(), dashLength = 10.dp.toPx())
            }
            .clickable { onAddButtonClicked() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_plus_circle),
            contentDescription = "Add",
            tint = TogedyTheme.colors.gray500
        )
    }
}

@Composable
fun ScheduleCategoryItem(
    name: String,
    isSelected: Boolean,
    onCategoryItemSelected: () -> Unit,
) {
    val borderColor = if (isSelected) TogedyTheme.colors.yellowMain else TogedyTheme.colors.gray300
    val textColor = if (isSelected) TogedyTheme.colors.black else TogedyTheme.colors.gray700

    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(TogedyTheme.colors.gray300)
            .border(2.dp, borderColor)
            .padding(4.dp)
            .clickable {
                //클릭 기능 구현
                onCategoryItemSelected()
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(TogedyTheme.colors.white)
                .padding(vertical = 2.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name,
                style = TogedyTheme.typography.overline,
                color = textColor
            )
        }
    }
}

fun DrawScope.drawDashedBorder(
    strokeWidth: Float,
    dashLength: Float,
    gapLength: Float = dashLength
) {
    val paint = Paint().apply {
        this.color = Color.Gray
        this.style = PaintingStyle.Stroke
        this.strokeWidth = strokeWidth
        this.pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength, gapLength), 0f)
    }

    val width = size.width - strokeWidth
    val height = size.height - strokeWidth
    drawRoundRect(
        color = Color.Gray,
        topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
        size = Size(width, height),
        cornerRadius = CornerRadius(8.dp.toPx()),
        style = Stroke(width = strokeWidth, pathEffect = paint.pathEffect)
    )
}


@Preview
@Composable
fun ScheduleCategoryPreview(modifier: Modifier = Modifier) {
    val calendarCategoryViewModel = CalendarCategoryViewModel()
    calendarCategoryViewModel.loadCategoryItems()
    CategorySelection(
        categories = calendarCategoryViewModel.categoryItems,
        onAddButtonClicked = { }
    )
}