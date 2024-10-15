package com.example.togedy_android.ui.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatViewModel: ViewModel() {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    fun postSendData(text : String){
        val updateMessages = _messages.value.toMutableList()
        updateMessages += ChatMessage(text, MessageType.Sent)
        _messages.value = updateMessages

        // 서버 호출 usecase 작성 예정
        val responseText = "서버에서 응답이 이렇게 올거에요"
        updateMessages += ChatMessage(responseText, MessageType.Received)
        _messages.value = updateMessages
    }
}