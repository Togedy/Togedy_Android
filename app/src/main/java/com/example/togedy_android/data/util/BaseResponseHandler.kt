package com.example.togedy_android.data.util

import com.example.togedy_android.data.remote.model.base.BaseResponse


fun <T> BaseResponse<T>.handleBaseResponse(): Result<T> {
    return when (this.responseCode) {
        in 200..299 -> {
            if (this.result != null) {
                Result.success(this.result)
            } else {
                Result.failure(Exception("No data available"))
            }
        }
        in 300..399 -> {
            Result.failure(Exception("Redirection error: ${this.responseMessage}"))
        }
        in 400..499 -> {
            Result.failure(Exception("Client error: ${this.responseMessage}"))
        }
        in 500..599 -> {
            Result.failure(Exception("Server error: ${this.responseMessage}"))
        }
        else -> {
            Result.failure(Exception("Unexpected error: ${this.responseMessage}"))
        }
    }
}