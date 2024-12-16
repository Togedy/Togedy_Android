package com.example.togedy_android.presentation.planner.setGoalTime

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togedy_android.domain.model.planner.NewStudyGoal
import com.example.togedy_android.domain.repository.PlannerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SetGoalTimeViewModel @Inject constructor(
    private val plannerRepository: PlannerRepository
) : ViewModel() {
    private val _isSetButtonActivated = MutableStateFlow<Boolean>(false)
    val isSetButtonActivated: StateFlow<Boolean> = _isSetButtonActivated

    fun postGoalTime(targetTime: String) {
        viewModelScope.launch {
            plannerRepository.postStudyGoal(
                NewStudyGoal(
                    date = LocalDate.now().toString(),
                    targetTime = targetTime
                )
            ).onSuccess {
                Log.d("SetGoalTimeAPI", "postGoalTime: 성공")
            }.onFailure {
                Log.d("SetGoalTimeAPI", "postGoalTime: 실패")
            }
        }
    }

    fun updateSetButtonActivation(newTime: String) {
        val result = isValidTimeFormat(newTime)
        _isSetButtonActivated.value = result
    }
}

fun isValidTimeFormat(input: String): Boolean {
    val regex = Regex("^\\d{2}:\\d{2}$")
    if (!regex.matches(input)) return false

    val (hours, minutes) = input.split(":").map { it.toIntOrNull() }
    return hours in 0..23 && minutes in 0..59
}