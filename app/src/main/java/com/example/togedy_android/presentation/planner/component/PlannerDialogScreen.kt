package com.example.togedy_android.presentation.planner.component

import androidx.compose.runtime.Composable
import com.example.togedy_android.domain.model.planner.StudyTag
import com.example.togedy_android.presentation.planner.planner.state.PlannerDialogState
import com.example.togedy_android.presentation.planner.planner.type.PlannerDialogType

/**
 * 신고 화면에서 등장하는 다이얼로그들을 관리하기 위한 스크린 컴포넌트
 *
 * @param dialogState 다이얼로그들의 가시성 관리를 위한 상태 클래스
 * @param onDismissRequest 확인/닫기/외부영역을 터치하면 발생하는 이벤트
 * @param onStudyTagConfirm 공부태그 추가 및 수정 완료 버튼 누르면 발생하는 이벤트
 * @param onCameraSelectionConfirm 사진 안내 다이얼로그의 확인 버튼을 누르면 발생하는 이벤트
 * @param onGallerySelectionConfirm 갤러리 선택 다이얼로그의 확인 버튼을 누르면 발생하는 이벤트
 * @param onResetReturnConfirm 뒤로 가기 다이얼로그의 확인 버튼을 누르면 발생하는 이벤트
 * @param onPhotoDeleteConfirm 사진 삭제 확인 버튼 클릭 시 호출되는 콜백
 * @param onSubmitComplete 신고 완료 후 "홈으로" 버튼 클릭 시 호출되는 콜백
 * @param onResetClick 신고 완료 후 닫기를 클릭하여 입력들을 초기화시키기 이벤트
 */

@Composable
fun PlannerDialogScreen(
    dialogState: PlannerDialogState,
    onDismissRequest: (PlannerDialogType) -> Unit,
    onStudyTagConfirm: (StudyTag) -> Unit,
) {
    with(dialogState) {
        if (isAddSubjectDialogVisible) {
            StudyTagDialog(
                title = "태그 추가하기",
                onDismissRequest = { onDismissRequest(PlannerDialogType.ADD_SUBJECT)},
                onSubmitButtonClicked = { onStudyTagConfirm(it) }
            )
        }
//        if (isAddSubjectDialogVisible) {
//            FireTruckIllegalParkingDialog(
//                onDismissRequest = { onDismissRequest(PlannerDialogType..ILLEGAL_PARKING) }
//            )
//        }
//
//        if (isAddPlanDialogVisible) {
//            MediaSelectionDialog(
//                onConfirmClick = {
//                    onCameraSelectionConfirm()
//                    onDismissRequest(ReportDialogType.CAMERA_SELECTION)
//                },
//                onDismissRequest = {
//                    onDismissRequest(ReportDialogType.CAMERA_SELECTION)
//                },
//            )
//        }

//        if (isGallerySelectionDialogVisible) {
//            MediaSelectionDialog(
//                onConfirmClick = {
//                    onGallerySelectionConfirm()
//                    onDismissRequest(ReportDialogType.GALLERY_SELECTION)
//                },
//                onDismissRequest = {
//                    onDismissRequest(ReportDialogType.GALLERY_SELECTION)
//                },
//            )
//        }
//
//        if (isPhotoDeleteDialogVisible) {
//            PhotoDeleteConfirmationDialog(
//                onConfirmClick = {
//                    onPhotoDeleteConfirm()
//                    onDismissRequest(ReportDialogType.PHOTO_DELETE)
//                },
//                onDismissRequest = { onDismissRequest(ReportDialogType.PHOTO_DELETE) }
//            )
//        }
//
//        if (isPhotoSizeLimitDialogVisible) {
//            PhotoSizeLimitDialog(
//                onDismissRequest = { onDismissRequest(ReportDialogType.PHOTO_SIZE_LIMIT) }
//            )
//        }
//
//        if (isPhotoTimeLimitDialogVisible) {
//            PhotoTimeLimitDialog(
//                onDismissRequest = { onDismissRequest(ReportDialogType.PHOTO_TIME_LIMIT) }
//            )
//        }
//
//        if (isResetConfirmationDialogVisible) {
//            ResetConfirmationDialog(
//                onConfirmClick = onResetReturnConfirm,
//                onDismissRequest = { onDismissRequest(ReportDialogType.RESET) }
//            )
//        }
//
//        if (isSubmitConfirmDialogVisible) {
//            SubmitConfirmDialog(
//                onDismissRequest = { onDismissRequest(ReportDialogType.SUBMIT_CONFIRM) },
//                onConfirmClick = {
//                    onDismissRequest(ReportDialogType.SUBMIT_CONFIRM)
//                    onSubmitConfirmClick()
//                }
//            )
//        }
//
//        if (isSubmitCompleteDialogVisible) {
//            SubmitCompleteDialog(
//                onHomeClick = onSubmitComplete,
//                onCloseClick = {
//                    onDismissRequest(ReportDialogType.SUBMIT)
//                    onResetClick()
//                },
//                onDismissRequest = {
//                    onDismissRequest(ReportDialogType.SUBMIT)
//                    onResetClick()
//                }
//            )
//        }
    }
}
