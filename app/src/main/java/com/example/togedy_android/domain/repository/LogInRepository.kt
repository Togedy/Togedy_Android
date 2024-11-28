package com.example.togedy_android.domain.repository

import com.example.togedy_android.data.model.response.LogInResponse

interface LogInRepository {
    suspend fun getLogInData(
        accessToken : String
    ): LogInResponse?
}