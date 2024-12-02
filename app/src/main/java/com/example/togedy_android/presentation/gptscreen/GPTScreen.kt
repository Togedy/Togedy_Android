package com.example.togedy_android.presentation.gptscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.togedy_android.R
import com.example.togedy_android.presentation.ui.model.ChatViewModel
import com.example.togedy_android.presentation.ui.model.MessageType
import com.example.togedy_android.core.design_system.theme.Togedy_AndroidTheme
import com.example.togedy_android.core.design_system.theme.defaultTogedyColors

@Composable
fun GPTScreen(
    navController: NavController,
    viewModel: ChatViewModel = viewModel()
) {
    Togedy_AndroidTheme {
        var chatText by remember { mutableStateOf("") }
        val messages by viewModel.messages.collectAsState()
        Column (
            modifier = Modifier
                .background(defaultTogedyColors.white)
                .fillMaxSize()
                .padding(20.dp),
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(messages) { message ->
                    if(message.type == MessageType.Sent){
                        Text(text = message.text,
                            modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.End),
                            color = Color.Blue)
                    } else {
                        Text(text = message.text,
                            modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start),
                            color = Color.Gray)
                    }
                }
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = chatText,
                onValueChange = { chatText = it },
                trailingIcon = {
                    Image(
                        painter = painterResource(R.drawable.ic_bottom_nav_community),
                        contentDescription = "전송 버튼",
                        modifier = Modifier.clickable(true){
                            viewModel.postSendData(chatText)
                            chatText = ""
                        })
                },
                placeholder = { Text(
                    stringResource(id = R.string.home_text_hint),
                    fontSize = 14.sp) },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                ),

            )
        }
    }
}

@Preview()
@Composable
fun PreviewGPTScreen() {
    val navController = rememberNavController()
    GPTScreen(navController = navController)
}