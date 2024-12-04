package com.example.togedy_android.domain.model

data class BoardWriting(
    val title: String,
    val content: String,
    val postImages: List<String> // 멀티 파트 변경 필요
)