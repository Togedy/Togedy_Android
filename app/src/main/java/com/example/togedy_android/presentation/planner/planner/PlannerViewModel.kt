package com.example.togedy_android.presentation.planner.planner

import androidx.lifecycle.ViewModel
import com.example.togedy_android.core.state.UiState
import com.example.togedy_android.domain.entity.PlannerHomeInformation
import com.example.togedy_android.domain.model.planner.DayOfPlan
import com.example.togedy_android.domain.model.planner.StudyTag
import com.example.togedy_android.presentation.planner.planner.state.PlannerDialogState
import com.example.togedy_android.presentation.planner.planner.state.PlannerUiState
import com.example.togedy_android.presentation.planner.planner.type.PlannerDialogType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PlannerViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(PlannerUiState())
    val uiState = _uiState.asStateFlow()

    private val _dialogState: MutableStateFlow<PlannerDialogState> =
        MutableStateFlow(PlannerDialogState())
    val dialogState: StateFlow<PlannerDialogState> = _dialogState.asStateFlow()

    fun getPlannerHomeInformation() {
        // get api 연결
        updateLoadState(
            loadState = UiState.Success(
                PlannerHomeInformation(
                    todaysPlan = DayOfPlan(
                        planList = emptyList(),
                        timeline = listOf(listOf("10:20", "14:43"), listOf("15:00", "16:03"))
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
        }
    }
}