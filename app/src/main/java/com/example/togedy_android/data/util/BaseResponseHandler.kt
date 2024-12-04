package com.example.togedy_android.data.util

import com.example.togedy_android.data.remote.model.base.BaseResponse


fun <T> BaseResponse<T>.handleBaseResponse(): Result<T> {
    return when (this.status) {
        in 200..299 -> {
            if (this.result != null) {
                Result.success(this.result)
            } else {
                Result.failure(Exception("No data available"))
            }
        }
        in 300..399 -> {
            Result.failure(Exception("Redirection error: ${this.message}"))
        }
        in 400..499 -> {
            Result.failure(Exception("Client error: ${this.message}"))
        }
        in 500..599 -> {
            Result.failure(Exception("Server error: ${this.message}"))
        }
        else -> {
            Result.failure(Exception("Unexpected error: ${this.message}"))
        }
    }
}