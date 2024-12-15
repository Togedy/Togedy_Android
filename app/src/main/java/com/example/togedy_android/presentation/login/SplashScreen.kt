package com.example.togedy_android.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.theme.TogedyTheme

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = TogedyTheme.colors.white),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.weight(2f))

        Text(
            text = "당신의 입시메이트",
            modifier = Modifier.padding(bottom = 17.dp),
            color = TogedyTheme.colors.gray400,
            style = TogedyTheme.typography.headline3M
        )

        Text(
            text = "Togedy",
            modifier = Modifier.padding(bottom = 50.dp),
            color = TogedyTheme.colors.black,
            style = TogedyTheme.typography.headline1B
        )

        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = "투게디 로고",
            modifier = Modifier.size(width = 93.dp, height = 67.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.fillMaxWidth()
                .height(210.dp)
                .clip(RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp))
                .background(TogedyTheme.colors.yellow100)
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview(){
    SplashScreen()
}