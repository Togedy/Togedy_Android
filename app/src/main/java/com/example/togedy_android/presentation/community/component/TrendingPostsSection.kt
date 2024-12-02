package com.example.togedy_android.presentation.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.core.design_system.component.TogedyTitle
import com.example.togedy_android.core.design_system.theme.TogedyTheme

@Composable
fun TrendingPostsSection(

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Box(modifier = Modifier.padding(start = 10.dp)) {
            TogedyTitle(text = "실시간 인기글")
        }
        Spacer(modifier = Modifier.height(10.dp))
        TrendingPostItem()
        Spacer(modifier = Modifier.height(12.dp))
        TrendingPostItem()
    }

}

@Composable
fun TrendingPostItem(

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape = RoundedCornerShape(8.dp), clip = false)
            .background(color = TogedyTheme.colors.white, shape = RoundedCornerShape(size = 10.dp))
            .padding(top = 8.dp, bottom = 14.dp)
            .padding(horizontal = 14.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = Color(0xFFFF6767), shape = RoundedCornerShape(size = 4.dp))
                .padding(horizontal = 4.dp, vertical = 2.dp)
        ) {
            Text(
                text = "HOT",
                style = TogedyTheme.typography.overline,
                color = TogedyTheme.colors.white
            )
        }
        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = "건국대학교",
                style = TogedyTheme.typography.overline,
                color = TogedyTheme.colors.gray400
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "제목부분입니다용가리",
                style = TogedyTheme.typography.body2B,
                color = TogedyTheme.colors.black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "내용이 들어가는 부분입니다용가리. 내용이 들어가는 부분입니다용가리. 내용이 들어가는 부분입니다용가리. 내용이 들어가는 부분입니다용가리. 내용이 들어가는 부분입니다용가리",
                style = TogedyTheme.typography.body3M,
                color = TogedyTheme.colors.gray600,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun TrendingPostsSectionPreview() {
    TrendingPostsSection()
}