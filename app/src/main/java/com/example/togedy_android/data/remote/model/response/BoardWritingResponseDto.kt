package com.example.togedy_android.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoardWritingResponseDto (
    @SerialName("postId") val postId: Int
)