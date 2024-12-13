package com.example.togedy_android.presentation.planner.plannerDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togedy_android.core.state.UiState
import com.example.togedy_android.domain.entity.PlannerHomeInformation
import com.example.togedy_android.domain.model.planner.DayOfPlan
import com.example.togedy_android.domain.model.planner.PlanItem
import com.example.togedy_android.domain.model.planner.StudyTag
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
    //repository 연결
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlannerDetailUiState())
    val uiState = _uiState.asStateFlow()

    private val _dialogState: MutableStateFlow<PlannerDialogState> =
        MutableStateFlow(PlannerDialogState())
    val dialogState: StateFlow<PlannerDialogState> = _dialogState.asStateFlow()

    fun updatePlanInfo(planItem: PlanItem) =
        _dialogState.update { currentState ->
            currentState.copy(
                planInfo = planItem
            )
        }

    suspend fun getDayPlanInformation(date: LocalDate){
        updateLoadState(
            loadState = UiState.Success(
                DayOfPlan(
                    planList = listOf(
                        PlanItem(
                            todoID = 1,
                            subjectId = 1,
                            subjectColor = "color10",
                            title = "국어 1강",
                            status = PlanState.NOT_STARTED.state
                        ),
                        PlanItem(
                            todoID = 1,
                            subjectId = 1,
                            subjectColor = "color10",
                            title = "국어 1강",
                            status = PlanState.NOT_STARTED.state
                        ),
                    ),
                    timeline = listOf(
                        listOf("07:00", "10:00"),
                        listOf("13:30", "14:13")
                    )
                )
            )
        )
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