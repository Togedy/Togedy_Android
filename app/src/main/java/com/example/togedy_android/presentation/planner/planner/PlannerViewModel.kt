package com.example.togedy_android.presentation.planner.planner

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togedy_android.core.state.UiState
import com.example.togedy_android.domain.entity.PlannerHomeInformation
import com.example.togedy_android.domain.model.planner.PlanItem
import com.example.togedy_android.domain.model.planner.StudyGoal
import com.example.togedy_android.domain.model.planner.StudyGoalDate
import com.example.togedy_android.domain.model.planner.StudyTag
import com.example.togedy_android.domain.repository.PlannerRepository
import com.example.togedy_android.domain.type.PlanState
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

    // 오늘의 목표량 조회
    fun getStudyGoal() {
        var initStudyGoal = StudyGoal(
            id = -1,
            targetTime = "00:00",
            actualTime = "00:00",
            achievement = 0,
        )
        val today = LocalDate.now().toString()
        viewModelScope.launch {
            plannerRepository.getStudyGoal(
                StudyGoalDate(today)
            ).onSuccess { studyGoal ->
                _uiState.value = _uiState.value.copy(
                    studyGoalState = UiState.Success(studyGoal)
                )
                initStudyGoal = studyGoal
            }.onFailure { throwable ->
                Log.d("API-PlannerViewModel", "getStudyGoal: 실패")
//                _uiState.value = _uiState.value.copy(
//                    studyGoalState = UiState.Success(initStudyGoal)
//                )
            }
        }
        updateLoadState(
            loadState = UiState.Success(
                PlannerHomeInformation(
                    studyGoal = initStudyGoal
                )
            )
        )
    }

    fun getPlannerHomeInformation() {
        // get api 연결
        updateLoadState(
            loadState = UiState.Success(
                PlannerHomeInformation(
//                    todaysPlan = DayOfPlan(
//                        planList = listOf(
//                            PlanItem(
//                                todoID = 1,
//                                subjectId = 1,
//                                subjectColor = "color10",
//                                title = "국어 1강",
//                                status = PlanState.NOT_STARTED.state
//                            )
//                        ),
//                        timeline = listOf(listOf("10:20", "14:43"), listOf("15:00", "16:03"))
//                    ),
                    planList = listOf(
                        PlanItem(
                            todoID = 1,
                            subjectId = 1,
                            subjectColor = "color10",
                            title = "국어 1강",
                            status = PlanState.NOT_STARTED.state
                        )
                    ),
                    studyTagList = listOf(
                        StudyTag(name = "국어", color = "color4"),
                        StudyTag(name = "수학", color = "color5"),
                        StudyTag(name = "국어", color = "color4"),
                        StudyTag(name = "수학", color = "color5"),
                        StudyTag(name = "국어", color = "color4"),
                        StudyTag(name = "수학", color = "color5"),
                        StudyTag(name = "국어", color = "color4"),
                        StudyTag(name = "수학", color = "color5"),
                    ),
                    studyGoal = StudyGoal(
                        -1, "", "", -1
                    )
                )
            )
        )
    }

    fun updateSelectedDay(selectedDay: LocalDate) =
        _uiState.update { currentState ->
            currentState.copy(
                selectedDay = selectedDay
            )
        }

    fun updateStudyTag(studyTag: StudyTag) =
        _dialogState.update { currentState ->
            currentState.copy(
                studyTagInfo = studyTag
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