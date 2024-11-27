package com.example.togedy_android.data.di

data class BaseResponse<T>(
    val success: Boolean,
    val code: Int,
    val message: String,
    val result: T
)
