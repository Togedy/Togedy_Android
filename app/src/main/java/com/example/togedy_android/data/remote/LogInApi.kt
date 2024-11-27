package com.example.togedy_android.data.remote

import com.example.togedy_android.data.di.BaseResponse
import com.example.togedy_android.data.model.response.LogInResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LogInApi {
    @GET("api/oauth/kakao")
    suspend fun logIn(
        @Query("accessToken") accessToken : String
    ) : BaseResponse<LogInResponse?>
}