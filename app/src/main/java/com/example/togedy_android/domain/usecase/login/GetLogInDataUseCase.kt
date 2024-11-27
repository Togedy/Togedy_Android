package com.example.togedy_android.domain.usecase.login

import com.example.togedy_android.data.model.response.LogInResponse
import com.example.togedy_android.domain.repository.LogInRepository
import javax.inject.Inject

class GetLogInDataUseCase @Inject constructor(
    private val logInRepository: LogInRepository
) {
    suspend operator fun invoke(accessToken : String) : LogInResponse? {
        return logInRepository.getLogInData(accessToken)
    }
}