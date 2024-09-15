package com.example.togedy_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.togedy_android.ui.BottomNavigationBar
import com.example.togedy_android.ui.theme.Togedy_AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Togedy_AndroidTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    BottomNavigationBar()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    BottomNavigationBar()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Togedy_AndroidTheme {
        Greeting()
    }
}