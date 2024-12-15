package com.example.togedy_android.presentation.planner.planner

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.togedy_android.core.state.UiState
import com.example.togedy_android.domain.entity.PlannerHomeInformation
import com.example.togedy_android.domain.model.planner.NewStudyTageItem
import com.example.togedy_android.domain.model.planner.PlanItem
import com.example.togedy_android.domain.model.planner.StudyGoal
import com.example.togedy_android.domain.model.planner.StudyGoalDate
import com.example.togedy_android.domain.model.planner.StudyTagItem
import com.example.togedy_android.domain.repository.PlannerRepository
import com.example.togedy_android.presentation.planner.planner.state.PlannerDialogState
import com.example.togedy_android.presentation.planner.planner.state.PlannerUiState
import com.example.togedy_android.presentation.planner.planner.type.PlannerDialogType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PlannerViewModel @Inject constructor(
    private val plannerRepository: PlannerRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(PlannerUiState())
    val uiState = _uiState.asStateFlow()

    private val _dialogState: MutableStateFlow<PlannerDialogState> =
        MutableStateFlow(PlannerDialogState())
    val dialogState: StateFlow<PlannerDialogState> = _dialogState.asStateFlow()

    fun getPlannerHomeInformation() {
        var studyGoal = StudyGoal(-1, "", "", 0)
        var studyTagList = emptyList<StudyTagItem>()
        val today = LocalDate.now().toString()
        viewModelScope.launch {
            // 공부 목표량
            plannerRepository.getStudyGoal(StudyGoalDate(today))
                .onSuccess { studyGoalItem ->
                    studyGoal = studyGoalItem
                }
                .onFailure { throwable ->
                    Log.d("API-PlannerViewModel", "getStudyGoal: 실패")
                }

            // 공부태그 리스트
            plannerRepository.getStudyTagList()
                .onSuccess { studyTagItemList ->
                    studyTagList = studyTagItemList
                }
                .onFailure { throwable ->
                    Log.d("API-PlannerViewModel", "getStudyTagList: 실패")
                }


            updateLoadState(
                loadState = UiState.Success(
                    PlannerHomeInformation(
                        studyGoal = studyGoal,
                        studyTagItemLists = studyTagList
                    )
                )
            )
        }
    }

    fun postStudyTag(studyTagItem: StudyTagItem) {
        viewModelScope.launch {
            plannerRepository.postStudyTag(
                request = NewStudyTageItem(
                    name = studyTagItem.name, color = studyTagItem.color
                )
            ).onSuccess {
                Log.d("API-PlannerViewModel", "postStudyTag: 성공")
                getPlannerHomeInformation()
            }.onFailure {
                Log.d("API-PlannerViewModel", "postStudyTag: 실패")
            }
        }
    }

    fun putStudyTag(studyTagItem: StudyTagItem) {
        viewModelScope.launch {
            plannerRepository.putStudyTag(
                tagId = dialogState.value.studyTagItemInfo.id,
                request = NewStudyTageItem(
                    name = studyTagItem.name, color = studyTagItem.color
                )
            ).onSuccess {
                Log.d("API-PlannerViewModel", "putStudyTag: 성공")
                getPlannerHomeInformation()
            }.onFailure {
                Log.d("API-PlannerViewModel", "putStudyTag: 실패")
            }
        }
    }

    fun updateSelectedDay(selectedDay: LocalDate) =
        _uiState.update { currentState ->
            currentState.copy(
                selectedDay = selectedDay
            )
        }

    fun updateStudyTag(studyTagItem: StudyTagItem) =
        _dialogState.update { currentState ->
            currentState.copy(
                studyTagItemInfo = studyTagItem
            )
        }

    fun updatePlanInfo(planItem: PlanItem) =
        _dialogState.update { currentState ->
            currentState.copy(
                planInfo = planItem
            )
        }

    private fun updateLoadState(loadState: UiState<PlannerHomeInformation>) =
        _uiState.update { currentState ->
            currentState.copy(
                loadState = loadState
            )
        }

    fun updateDialogVisibility(type: PlannerDialogType) {
        when (type) {
            PlannerDialogType.ADD_SUBJECT -> {
                _dialogState.update {
                    it.copy(isAddSubjectDialogVisible = !_dialogState.value.isAddSubjectDialogVisible)
                }
            }

            PlannerDialogType.EDIT_SUBJECT -> {
                _dialogState.update {
                    it.copy(isEditSubjectDialogVisible = !_dialogState.value.isEditSubjectDialogVisible)
                }
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