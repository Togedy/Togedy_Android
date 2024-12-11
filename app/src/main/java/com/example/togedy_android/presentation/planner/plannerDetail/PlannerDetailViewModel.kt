package com.example.togedy_android.presentation.planner.plannerDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togedy_android.core.state.UiState
import com.example.togedy_android.domain.model.planner.DayOfPlan
import com.example.togedy_android.presentation.planner.plannerDetail.state.PlannerDetailIntent
import com.example.togedy_android.presentation.planner.plannerDetail.state.PlannerDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PlannerDetailViewModel @Inject constructor(
    //repository 연결
): ViewModel() {

    private val _uiState: MutableStateFlow<PlannerDetailUiState> = MutableStateFlow(PlannerDetailUiState())
    val uiState get() = _uiState.asStateFlow()

    fun processIntent(intent: PlannerDetailIntent) {
        when (intent) {
            is PlannerDetailIntent.LoadDayPlan -> loadDayPlan(intent.date)
        }
    }

    private fun loadDayPlan(date: LocalDate) = viewModelScope.launch {
        _uiState.update { it.copy(loadState = UiState.Loading, selectedDay = date) }

        try {
            // TODO: 실제 데이터 로드 로직 추가
            val dayOfPlan = getDayPlanFromRepository(date) // Repository 호출
            _uiState.update { it.copy(loadState = UiState.Success(dayOfPlan)) }
        } catch (e: Exception) {
            _uiState.update { it.copy(loadState = UiState.Error(e.message ?: "Unknown error")) }
        }
    }

    private suspend fun getDayPlanFromRepository(date: LocalDate): DayOfPlan {
        // Repository에서 데이터를 가져오는 함수
        return DayOfPlan(
            planList = emptyList(),
            timeline = listOf(
                listOf("07:00", "10:00"),
                listOf("13:30", "14:13")
            )
        )
    }
}