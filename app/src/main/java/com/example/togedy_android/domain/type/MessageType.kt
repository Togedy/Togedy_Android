package com.example.togedy_android.domain.type

enum class MessageType { Sent, Received }
data class ChatMessage(
    val text: String, val type: MessageType
)
