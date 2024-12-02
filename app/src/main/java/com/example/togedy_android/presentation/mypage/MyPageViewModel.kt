package com.example.togedy_android.presentation.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togedy_android.data.model.response.LogInResponse
import com.example.togedy_android.domain.usecase.login.GetLogInDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getLogInDataUseCase: GetLogInDataUseCase
): ViewModel(){
    private val _logInData = MutableLiveData<LogInResponse?>()
    val logInData: LiveData<LogInResponse?> = _logInData

    fun loadLogInData(accessToken : String){
        viewModelScope.launch {
            try {
                val tokenData = getLogInDataUseCase(accessToken)
                _logInData.value = tokenData
                Log.d("SearchViewModel", "로드된 데이터: ${_logInData.value}")

            } catch (e : Exception){
                Log.e("LogInViewModel", "loadLogInData 오류", e)
            }
        }
    }
}