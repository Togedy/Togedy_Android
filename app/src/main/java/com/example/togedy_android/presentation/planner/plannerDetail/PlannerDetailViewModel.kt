package com.example.togedy_android.presentation.planner.plannerDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togedy_android.core.state.UiState
import com.example.togedy_android.domain.model.planner.Date
import com.example.togedy_android.domain.model.planner.DayOfPlan
import com.example.togedy_android.domain.model.planner.NewStudyPlan
import com.example.togedy_android.domain.model.planner.PlanItem
import com.example.togedy_android.domain.repository.PlannerRepository
import com.example.togedy_android.domain.type.PlanState
import com.example.togedy_android.presentation.planner.planner.state.PlannerDialogState
import com.example.togedy_android.presentation.planner.planner.type.PlannerDialogType
import com.example.togedy_android.presentation.planner.plannerDetail.state.PlannerDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PlannerDetailViewModel @Inject constructor(
    private val plannerRepository: PlannerRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(PlannerDetailUiState())
    val uiState = _uiState.asStateFlow()

    private val _dialogState: MutableStateFlow<PlannerDialogState> =
        MutableStateFlow(PlannerDialogState())
    val dialogState: StateFlow<PlannerDialogState> = _dialogState.asStateFlow()

//    fun updatePlanInfo(planItem: NewStudyPlan) =
//        _dialogState.update { currentState ->
//            currentState.copy(
//                planInfo = planItem
//            )
//        }

    fun getDayPlanInformation(date: LocalDate) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedDay = date
            )
        }
        viewModelScope.launch {
            plannerRepository.getStudyPlanList(Date(date.toString()))
                .onSuccess { studyPlanItemList ->
                    updateLoadState(
                        loadState = UiState.Success(
                            DayOfPlan(
                                planList = studyPlanItemList
                            )
                        )
                    )
                }
                .onFailure { throwable ->
                    Log.d("API-PlannerDetailViewModel", "getDayPlanInformation: ${throwable.message}")
                    Log.d("API-PlannerDetailViewModel", "getStudyPlanList: 실패")
                    updateLoadState(
                        loadState = UiState.Success(
                            DayOfPlan(
                                planList = emptyList()
                            )
                        )
                    )
                }
        }
    }

    fun postStudyPlan(studyPlanItem: NewStudyPlan) {
        viewModelScope.launch {
            plannerRepository.postStudyPlan(request = studyPlanItem)
                .onSuccess {
                    Log.d("API-PlannerDetailViewModel", "postStudyPlan: 성공")
                    getDayPlanInformation(_uiState.value.selectedDay)
                }.onFailure {
                    Log.d("API-PlannerDetailViewModel", "postStudyPlan: 실패")
                }
        }
    }

    private fun updateLoadState(loadState: UiState<DayOfPlan>) =
        _uiState.update { currentState ->
            currentState.copy(
                loadState = loadState
            )
        }

    fun updateDialogVisibility(type: PlannerDialogType) {
        when (type) {
            PlannerDialogType.ADD_SUBJECT -> {
                /* 이 화면에서는 존재 X */
            }

            PlannerDialogType.EDIT_SUBJECT -> {
                /* 이 화면에서는 존재 X */
            }

            PlannerDialogType.ADD_PLAN -> {
                _dialogState.update {
                    it.copy(isAddPlanDialogVisible = !_dialogState.value.isAddPlanDialogVisible)
                }
            }

            PlannerDialogType.EDIT_PLAN -> {
                _dialogState.update {
                    it.copy(isEditPlanDialogVisible = !_dialogState.value.isEditPlanDialogVisible)
                }
            }

            PlannerDialogType.EDIT_PLAN_STATE -> {
                _dialogState.update {
                    it.copy(isEditPlanStateDialogVisible = !_dialogState.value.isEditPlanStateDialogVisible)
                }
            }
        }
    }
}