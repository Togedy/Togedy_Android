package com.example.togedy_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.togedy_android.ui.theme.Togedy_AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Togedy_AndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),

    ) {
        Text(text = "투게디 시작!")
    }
}