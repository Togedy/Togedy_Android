package com.example.togedy_android.data.util

import android.util.Log
import com.example.togedy_android.data.remote.model.base.BaseResponse


fun <T> BaseResponse<T>.handleBaseResponse(): Result<T> {
    Log.d("BaseResponse", "isSuccess: $isSuccess, responseCode: $responseCode, result: $result")
    return if (isSuccess) {
        result?.let { Result.success(it) } ?: Result.failure(Exception("result:null 입니다."))
    } else {
        Result.failure(Exception(responseMessage))
    }
}
