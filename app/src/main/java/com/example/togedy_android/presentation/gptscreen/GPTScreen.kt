package com.example.togedy_android.presentation.gptscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.core.design_system.theme.Togedy_AndroidTheme
import com.example.togedy_android.domain.type.MessageType
import com.example.togedy_android.presentation.gptscreen.component.InitialGPTScreen
import com.example.togedy_android.presentation.gptscreen.component.MessageText
import com.example.togedy_android.presentation.gptscreen.component.MessageTextField

@Composable
fun GPTScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = viewModel()
) {
    val messages by viewModel.messages.collectAsState()

    Column(
        modifier = modifier
            .background(TogedyTheme.colors.white)
            .fillMaxSize()
            .padding(20.dp),
    ) {
        if (messages.isEmpty()) {
            InitialGPTScreen(
                modifier = Modifier.weight(1f)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(messages) { message ->
                    val isSent = message.type == MessageType.Sent
                    MessageText(
                        text = message.text,
                        isSent = isSent
                    )
                }
            }
        }

        MessageTextField()
    }
}

@Preview()
@Composable
fun PreviewGPTScreen() {
    Togedy_AndroidTheme() {
        GPTScreen()
    }
}