package com.example.togedy_android.ui.model

enum class MessageType { Sent, Received }
data class ChatMessage(
    val text: String, val type: MessageType
)
