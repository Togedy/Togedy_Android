package com.example.togedy_android.domain.model

data class BoardDetail(
    val title: String ,
    val createdAt: String,
    val content: String,
    val postImages: List<String>,
    val likeCount: Int,
    val commentCount: Int,
    val comments: List<DetailComments>,
    val postLike: Boolean
)

data class DetailComments(
    val commentId: String,
    val userProfileImg: String?,
    val userName: String,
    val content: String,
    val likeCount: Int,
    val commentStatus: String,
    val replies: List<DetailReplies>,
    val commentLike: Boolean
)

data class DetailReplies(
    val commentId: String,
    val userProfileImg: String?,
    val userName: String,
    val content: String,
    val likeCount: Int,
    val commentStatus: String,
    val replies: List<String>,
    val commentLike: Boolean
)