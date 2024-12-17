package com.example.togedy_android.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoardDetailResponseDto (
    @SerialName("title") val title: String,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("content") val content: String,
    @SerialName("postImages") val postImages: List<String>,
    @SerialName("likeCount") val likeCount: Int,
    @SerialName("commentCount") val commentCount: Int,
    @SerialName("comments") val comments: List<DetailCommentsDto>,
    @SerialName("postLike") val postLike: Boolean
)

@Serializable
data class DetailCommentsDto(
    @SerialName("commentId") val commentId: Int,
    @SerialName("userProfileImg") val userProfileImg: String?,
    @SerialName("userName") val userName: String,
    @SerialName("content") val content: String,
    @SerialName("likeCount") val likeCount: Int,
    @SerialName("commentStatus") val commentStatus: String,
    @SerialName("replies") val replies: List<DetailRepliesDto>,
    @SerialName("commentLike") val commentLike: Boolean
)

@Serializable
data class DetailRepliesDto(
    @SerialName("commentId") val commentId: Int,
    @SerialName("userProfileImg") val userProfileImg: String?,
    @SerialName("userName") val userName: String,
    @SerialName("content") val content: String,
    @SerialName("likeCount") val likeCount: Int,
    @SerialName("commentStatus") val commentStatus: String,
    @SerialName("replies") val replies: List<String>,
    @SerialName("commentLike") val commentLike: Boolean
)