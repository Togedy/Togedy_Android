package com.example.togedy_android.data.remote.model.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val isSuccess: Boolean,
    val responseCode: Int,
    val responseMessage: String,
    val result: T? = null
)