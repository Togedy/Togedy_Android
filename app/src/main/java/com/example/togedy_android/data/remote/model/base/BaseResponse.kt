package com.example.togedy_android.data.remote.model.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val result: T?
)