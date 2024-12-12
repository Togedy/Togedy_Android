package com.example.togedy_android.presentation.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.domain.type.WritingType
import com.example.togedy_android.presentation.community.CommunityViewModel

@Composable
fun CommunityFastTab(
    navigateToCommunityBoard: (String, String?) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(142.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFAC14F),
                        Color(0xFFFFDF7D)
                    ),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                ),
                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommunityFastTabItem(
                icon = R.drawable.ic_annotation_dots_light_line,
                label = "공부인증글",
                boardType = WritingType.STUDY_RECORD,
                navigateToCommunityBoard = navigateToCommunityBoard
            )
            CommunityFastTabItem(
                icon = R.drawable.ic_building_light_line,
                label = "대학별 게시판",
                boardType = WritingType.KONKUK_UNIV,
                navigateToCommunityBoard = navigateToCommunityBoard
            )
            CommunityFastTabItem(
                icon = R.drawable.ic_shopping_bag_light_line,
                label = "장터게시판",
                boardType = WritingType.MARKETPLACE,
                navigateToCommunityBoard = navigateToCommunityBoard
            )
        }
    }
}

@Composable
fun CommunityFastTabItem(
    icon: Int,
    label: String,
    viewModel: CommunityViewModel = hiltViewModel(),
    boardType: WritingType,
    navigateToCommunityBoard: (String, String?) -> Unit
) {
    Column(
        modifier = Modifier
            .width(90.dp)
            .height(100.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                val type = viewModel.getBoardPath(boardType)
                navigateToCommunityBoard(type, if(type != "univ") null else boardType.type) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFF8501),
                            Color(0xFFFFAB11)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    ),
                    shape = RoundedCornerShape(50.dp)
                )
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
                tint = TogedyTheme.colors.white,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            style = TogedyTheme.typography.body3B,
            color = TogedyTheme.colors.gray600
        )
    }
}


@Preview
@Composable
fun CommunityFastTabPreview() {
    CommunityFastTab(
        navigateToCommunityBoard = { boardType, univName ->
            println("boardType: $boardType, univName: $univName")
        }
    )
}
