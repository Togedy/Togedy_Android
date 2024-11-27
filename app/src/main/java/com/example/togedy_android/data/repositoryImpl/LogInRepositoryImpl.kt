package com.example.togedy_android.data.repositoryImpl

import android.util.Log
import com.example.togedy_android.data.model.response.LogInResponse
import com.example.togedy_android.data.remote.LogInApi
import com.example.togedy_android.domain.repository.LogInRepository
import javax.inject.Inject

class LogInRepositoryImpl @Inject constructor(
    private val logInApi: LogInApi
) : LogInRepository {
    override suspend fun getLogInData(accessToken : String): LogInResponse? {
        return try {
            val response = logInApi.logIn(accessToken)
            if (response.success) {
                response.result
            } else {
                Log.e("logInData", response.message)
                null
            }
        } catch (e: Exception) {
            Log.e("logInData", "logIn", e)
            null
        }
    }
}