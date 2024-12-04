package com.example.togedy_android.presentation.gptscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togedy_android.domain.type.ChatMessage
import com.example.togedy_android.domain.type.MessageType
import com.example.togedy_android.presentation.gptscreen.retrofit.ChatRequest
import com.example.togedy_android.presentation.gptscreen.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    fun postSendData(text: String) {
        val updateMessages = _messages.value.toMutableList()
        updateMessages += ChatMessage(text, MessageType.Sent)
        _messages.value = updateMessages

        viewModelScope.launch {
            val tempMessages = updateMessages.toMutableList()
            try {
                val response = RetrofitInstance.api.sendMessage(ChatRequest(query = text))
                tempMessages += ChatMessage(response.content, MessageType.Received)
                _messages.value = tempMessages // UI 상태 즉시 업데이트
            } catch (e: Exception) {
                tempMessages += ChatMessage("서버 연결 실패: ${e.message}", MessageType.Received)
                _messages.value = tempMessages
            }
        }
    }
}
