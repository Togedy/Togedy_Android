package com.example.togedy_android.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoardListResponseDto(
        @SerialName("title") val title: String,
        @SerialName("createdAt") val createdAt: String,
        @SerialName("content") val content: String,
        @SerialName("postImages") val postImages: List<String>
)