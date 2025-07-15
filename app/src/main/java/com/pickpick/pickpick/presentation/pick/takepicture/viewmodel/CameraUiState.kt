package com.pickpick.pickpick.presentation.pick.takepicture.viewmodel

import androidx.camera.core.ImageCapture
import com.pickpick.pickpick.FrameLayout
import com.pickpick.pickpick.core.presentation.UiState
import com.pickpick.pickpick.presentation.pick.selectslot.viewmodel.SlotLayout

data class CameraUiState(
    val capturedPhotoUri: String? = null,
    val frameLayout: FrameLayout = FrameLayout(),
    // todo 정확한 타입 설정시 변경. id, profileImage...
    // 슬롯 비율로된 데이터
    val ratioSlotLayouts: List<SlotLayout> = emptyList(),
    // 현재 선택된 슬롯 인덱스
    val slotLayouts: List<SlotLayout> = emptyList(),
    val imageCapture: ImageCapture? = null,
) : UiState
