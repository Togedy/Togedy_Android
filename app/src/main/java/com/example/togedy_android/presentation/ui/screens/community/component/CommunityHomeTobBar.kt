package com.example.togedy_android.presentation.ui.screens.community.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.ui.theme.TogedyTheme

@Composable
fun CommunityHomeTopBar(
    onMenuBtnClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = "로고이미지",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "Togedy",
            style = TogedyTheme.typography.headline3B
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_menu),
            contentDescription = "메뉴버튼",
            modifier = Modifier.clickable {
                onMenuBtnClicked()
            }
        )
    }
}