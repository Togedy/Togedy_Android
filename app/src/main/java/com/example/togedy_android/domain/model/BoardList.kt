package com.example.togedy_android.domain.model

data class BoardList(
    val postId: Int,
    val title: String,
    val createdAt: String,
    val content: String,
    val postImages: List<String>? = null
)