package com.example.togedy_android.presentation.gptscreen.retrofit

import retrofit2.http.Body
import retrofit2.http.POST

data class ChatRequest(val query: String)
data class ChatResponse(val content: String)

interface ChatApiService {
    @POST("ChatAI")
    suspend fun sendMessage(
        @Body request: ChatRequest
    ): ChatResponse
}
