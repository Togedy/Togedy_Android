package com.example.togedy_android.presentation.ui.bottom_nav

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.togedy_android.R

@Composable
fun DefaultBtn(
    navController: NavController,
    route: String,
    value: String,
    btnName: String,
    activate: Boolean,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        onClick = {
            if (activate)  {
                if (value.isNullOrEmpty()) {
                    navController.navigate(route)
                } else { //name값이 올때
                    navController.navigate("$route?title=$value")
                }
            } else { }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (activate) colorResource(id = R.color.yellow_main) else colorResource(
                id = R.color.gray400
            ),
        )
    ) {
        Text(
            text = btnName,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(500),
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center,
        )
    }
}