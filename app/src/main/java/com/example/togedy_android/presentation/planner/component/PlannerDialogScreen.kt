package com.example.togedy_android.presentation.planner.component

import androidx.compose.runtime.Composable
import com.example.togedy_android.domain.model.planner.PlanItem
import com.example.togedy_android.domain.model.planner.StudyTagItem
import com.example.togedy_android.presentation.planner.planInfoDialog.PlanInfoDialog
import com.example.togedy_android.presentation.planner.planner.state.PlannerDialogState
import com.example.togedy_android.presentation.planner.planner.type.PlannerDialogType

/**
 * 플래너 화면에 다이얼로그 관리 컴포넌트
 *
 * @param dialogState 다이얼로그들의 가시성 관리를 위한 상태 클래스
 * @param onDismissRequest 확인/닫기/외부영역을 터치하면 발생하는 이벤트
 * @param onStudyTagConfirm 공부태그 추가 완료 버튼 누르면 발생하는 이벤트
 * @param onStudyTagEditConfirm 공부태그 수정 완료 버튼 누르면 발생하는 이벤트
 */

@Composable
fun PlannerDialogScreen(
    dialogState: PlannerDialogState,
    onDismissRequest: (PlannerDialogType) -> Unit,
    onStudyTagConfirm: (StudyTagItem) -> Unit,
    onStudyTagEditConfirm: (StudyTagItem) -> Unit,
    onPlanAddConfirm: (PlanItem) -> Unit,
    onPlanEditConfirm: (PlanItem) -> Unit,

    ) {
    with(dialogState) {
        if (isAddSubjectDialogVisible) {
            StudyTagDialog(
                title = "태그 추가하기",
                onDismissRequest = { onDismissRequest(PlannerDialogType.ADD_SUBJECT) },
                onSubmitButtonClicked = {
                    onStudyTagConfirm(it)
                    onDismissRequest(PlannerDialogType.ADD_SUBJECT)
                }
            )
        }

        if (isEditSubjectDialogVisible) {
            StudyTagDialog(
                title = "태그 수정하기",
                studyTagItem = dialogState.studyTagItemInfo,
                onDismissRequest = { onDismissRequest(PlannerDialogType.EDIT_SUBJECT) },
                onSubmitButtonClicked = {
                    onStudyTagEditConfirm(it)
                    onDismissRequest(PlannerDialogType.EDIT_SUBJECT)
                }
            )
        }

        if (isAddPlanDialogVisible) {
            PlanInfoDialog(
                title = "플랜 추가하기",
                onDismissRequest = { onDismissRequest(PlannerDialogType.ADD_PLAN) },
                onSubmitButtonClicked = {
                    onPlanAddConfirm(it)
                    onDismissRequest(PlannerDialogType.ADD_PLAN)
                }
            )
        }

        if (isEditPlanDialogVisible) {
            PlanInfoDialog(
                title = "플랜 수정하기",
                planItem = dialogState.planInfo,
                onDismissRequest = { onDismissRequest(PlannerDialogType.EDIT_PLAN) },
                onSubmitButtonClicked = {
                    onPlanEditConfirm(it)
                    onDismissRequest(PlannerDialogType.EDIT_PLAN)
                }
            )
        }
    }
}
