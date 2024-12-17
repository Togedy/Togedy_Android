package com.example.togedy_android.presentation.gptscreen

enum class MessageType { Sent, Received }
data class ChatMessage(
    val text: String, val type: MessageType
)
