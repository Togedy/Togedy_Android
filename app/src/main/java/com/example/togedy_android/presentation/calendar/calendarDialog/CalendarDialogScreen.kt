package com.example.togedy_android.presentation.calendar.calendarDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.presentation.calendar.calendar.state.CalendarDialogState
import com.example.togedy_android.R
import com.example.togedy_android.domain.model.calendar.DailyScheduleItem
import com.example.togedy_android.presentation.calendar.component.CalendarFloatingBtn
import com.example.togedy_android.util.formatToDotSeparatedDate
import com.example.togedy_android.util.formatToKoreanDate
import com.example.togedy_android.util.formatToSimpleDate
import com.example.togedy_android.util.noRippleClickable
import com.example.togedy_android.util.toColor
import java.time.LocalDate

@Composable
fun CalendarDialogScreen(
    dialogState: CalendarDialogState,
    onDismissRequest: () -> Unit,
//    onEditButtonClicked: (Int) -> Unit,
    onPlanAddButtonClicked: () -> Unit,
) {
    with(dialogState) {
        if (isDailyScheduleDialogVisible) {
            DailyScheduleDialog(
                selectedDay = dialogState.selectedDay,
                onDismissRequest = onDismissRequest,
                onEditButtonClicked = { /* 추후 편집 페이지 연결 : onEditButtonClicked */ },
                onPlanAddButtonClicked = onPlanAddButtonClicked
            )
        }
    }
}

@Composable
fun DailyScheduleDialog(
    selectedDay: LocalDate,
    onDismissRequest: () -> Unit,
    onEditButtonClicked: (Int) -> Unit,
    onPlanAddButtonClicked: () -> Unit,
    viewModel: CalendarDialogViewModel = hiltViewModel()
) {
    val dailyScheduleItems by viewModel.scheduleItems.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getDailyScheduleItemList(selectedDay)
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .size(335.dp)
                .background(
                    color = TogedyTheme.colors.white,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(20.dp)
                .padding(horizontal = 2.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row {
                Text(
                    text = selectedDay.toString().formatToKoreanDate(),
                    style = TogedyTheme.typography.headline3B,
                    color = TogedyTheme.colors.black
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "",
                    style = TogedyTheme.typography.body1R,
                    color = TogedyTheme.colors.darkblue200
                )
            }

            Spacer(Modifier.height(18.dp))

            ScheduleBlock(
                Modifier.weight(1f),
                dailyScheduleItems
            )

            Spacer(Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                CalendarFloatingBtn(
                    onButtonClicked = onPlanAddButtonClicked
                )
            }
        }
    }
}

@Composable
fun ScheduleBlock(
    modifier: Modifier,
    dailyScheduleItems: List<DailyScheduleItem>
) {
    Column(
        modifier = modifier
    ) {
        if (dailyScheduleItems.isEmpty()) {
            Text(
                text = "일정이 없습니다.",
                style = TogedyTheme.typography.body1R,
                color = TogedyTheme.colors.gray600
            )
        } else {
            DailyScheduleItemsSection(
                dailyScheduleItems = dailyScheduleItems
            )
        }
    }
}

@Composable
fun DailyScheduleItemsSection(
    dailyScheduleItems: List<DailyScheduleItem>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(dailyScheduleItems) { dailyScheduleItem ->
            var isSelected by remember { mutableStateOf(false) }
            DailyScheduleItemBlock(
                dailyScheduleItem = dailyScheduleItem,
                isSelected = isSelected,
                onBlockClicked = { isSelected = !isSelected }
            )
        }
    }
}

@Composable
fun DailyScheduleItemBlock(
    dailyScheduleItem: DailyScheduleItem,
    isSelected: Boolean,
    onBlockClicked: () -> Unit,
) {
    val color = dailyScheduleItem.category.color.toColor() ?: TogedyTheme.colors.gray200

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = color, shape = RoundedCornerShape(size = 8.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp)
            .noRippleClickable(
                onClick = onBlockClicked
            )
    ) {
        Text(
            text = dailyScheduleItem.category.name,
            style = TogedyTheme.typography.overline,
            color = TogedyTheme.colors.gray600
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = dailyScheduleItem.name,
            style = TogedyTheme.typography.body3B,
            color = TogedyTheme.colors.black
        )

        if (isSelected) {
            Spacer(Modifier.height(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_calendar),
                    contentDescription = stringResource(R.string.calendar_btn_calendar_description),
                    tint = TogedyTheme.colors.black,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = dailyScheduleItem.startDate.toString().formatToDotSeparatedDate(),
                    style = TogedyTheme.typography.body3M,
                    color = TogedyTheme.colors.black
                )
                if (dailyScheduleItem.endDate != null) {
                    Text(
                        text = " - ${dailyScheduleItem.startDate.toString().formatToDotSeparatedDate()}",
                        style = TogedyTheme.typography.body3M,
                        color = TogedyTheme.colors.black
                    )
                }
            }

            Spacer(Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0x66000000),
                            shape = RoundedCornerShape(size = 6.dp)
                        )
                        .padding(horizontal = 14.dp, vertical = 7.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "편집",
                        style = TogedyTheme.typography.body3M,
                        color = TogedyTheme.colors.gray700
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DailyScheduleDialogPreview() {
    DailyScheduleDialog(
        selectedDay = LocalDate.now(),
        onDismissRequest = { },
        onEditButtonClicked = { },
        onPlanAddButtonClicked = { },
    )
}