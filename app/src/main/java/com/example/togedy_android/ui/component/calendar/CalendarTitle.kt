package com.example.togedy_android.ui.component.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.togedy_android.R
import java.time.LocalDate

@Composable
fun TitleYear() {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = LocalDate.now().year.toString(),
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontWeight = FontWeight(400),
                textAlign = TextAlign.Center,
            )
        )
        Image(
            painter = painterResource(id = R.drawable.ic_chevron_selector_vertical),
            contentDescription = "뒤로가기",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(15.dp)
                .clickable { }
        )
    }
}

@Composable
fun TitleMonth() {
    Row(
        modifier = Modifier
            .padding(top = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_chevron_left),
            contentDescription = "이전달",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(24.dp)
                .clickable { }
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = LocalDate.now().month.toString(),
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 26.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                fontWeight = FontWeight(700),
                textAlign = TextAlign.Center,
            )
        )
        Spacer(modifier = Modifier.width(4.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_chevron_right),
            contentDescription = "다음달",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(24.dp)
                .clickable { }
        )
    }
}

@Composable
fun TitleDayOfWeek() {
    val daysOfWeek = listOf("일", "월", "화", "수", "목", "금", "토")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var color = R.color.black
        for (day in daysOfWeek) {
            color = if (day == "일") R.color.error else if (day == "토") R.color.main_blue else R.color.black

            Text(
                text = day,
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                    fontWeight = FontWeight(700),
                    color = colorResource(id = color),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.width(20.dp)
            )
        }
    }
}
