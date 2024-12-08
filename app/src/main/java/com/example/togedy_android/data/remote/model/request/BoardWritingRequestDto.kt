package com.example.togedy_android.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoardWritingRequestDto (
    @SerialName("title") val title: String,
    @SerialName("content") val content: String,
    @SerialName("postImages") val postImages: List<String> // 멀티 파트 변경 필요
)