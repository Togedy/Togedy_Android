package com.example.togedy_android.presentation.planner.stopwatch

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.togedy_android.core.design_system.component.TopBarBasic
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.TogedyButtonBasic
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun StopWatchScreen(
    onCloseButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var startTime = ""
    var endTime = ""
    var timeInSeconds by remember { mutableIntStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (true) {
                kotlinx.coroutines.delay(1000L)
                timeInSeconds++
            }
        }
    }

    Column(
        modifier = modifier
            .background(TogedyTheme.colors.black)
            .fillMaxSize()
            .padding(top = 20.dp)
            .padding(horizontal = 20.dp)
    ) {
        TopBarBasic(
            leftButtonIcon = R.drawable.ic_x_close,
            title = stringResource(R.string.stop_watch_title),
            onLeftButtonClicked = onCloseButtonClicked,
            textColor = TogedyTheme.colors.white
        )

        Spacer(Modifier.weight(1f))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = TogedyTheme.colors.gray400,
                    shape = RoundedCornerShape(size = 20.dp)
                )
                .padding(vertical = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.stop_watch_time_steam),
                style = TogedyTheme.typography.headline3R,
                color = TogedyTheme.colors.darkblue200
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = if (isRunning) formatTime(timeInSeconds) else "00:00:00",
                style = TextStyle(
                    fontSize = 40.sp,
                    lineHeight = 36.sp,
                    fontWeight = FontWeight(700),
                    color = TogedyTheme.colors.white,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.stop_watch_total_time),
                style = TogedyTheme.typography.headline3B,
                color = TogedyTheme.colors.darkblue200
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "00:00:00",
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 26.sp,
                    fontWeight = FontWeight(700),
                    color = TogedyTheme.colors.white,
                    textAlign = TextAlign.Center
                )
            )
        }

        Spacer(Modifier.weight(1f))

        TogedyButtonBasic(
            buttonText = if (isRunning) "STOP" else "START",
            isActivated = !isRunning,
            onButtonClick = {
                isRunning = !isRunning
                startTime = LocalDateTime.now().format(formatTimeForServer)
                Log.d("chrin", "StopWatchScreen: startTime $startTime")
            },
            onNotActivatedButtonClick = {
                isRunning = !isRunning
                endTime = LocalDateTime.now().format(formatTimeForServer)
                Log.d("chrin", "StopWatchScreen: endTime $endTime")
                timeInSeconds = 0
//                time = 0
                //서버 연결
            }
        )

        Spacer(Modifier.weight(1f))
    }
}

@SuppressLint("DefaultLocale")
fun formatTime(seconds: Int): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
}


val formatTimeForServer = DateTimeFormatter.ofPattern("HH:mm")


@Preview
@Composable
fun StopWatchScreenPreview() {
    StopWatchScreen(
        onCloseButtonClicked = { }
    )
}