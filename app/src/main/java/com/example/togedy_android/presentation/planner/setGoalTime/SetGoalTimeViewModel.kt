package com.example.togedy_android.presentation.planner.setGoalTime

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SetGoalTimeViewModel : ViewModel() {
    private val _isSetButtonActivated = MutableStateFlow<Boolean>(false)
    val isSetButtonActivated: StateFlow<Boolean> = _isSetButtonActivated

    fun postGoalTime(newGoalTime: String) {
        //api 연결
    }

    fun updateSetButtonActivation(result: Boolean) {
        //goalTime의 형식 점검 로직 추가 필요
        _isSetButtonActivated.value = result
    }
}