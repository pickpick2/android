package com.pickpick.pickpick.presentation.pick.captureresult.viewmodel

import com.pickpick.pickpick.core.presentation.UiState
import com.pickpick.pickpick.core.ui.component.pickpick.slot.SlotType.CameraResult
import com.pickpick.pickpick.domain.pick.model.FrameLayout
import com.pickpick.pickpick.presentation.pick.selectslot.viewmodel.SlotLayout

data class CaptureResultUiState(
    val frameLayout: FrameLayout = FrameLayout(),
    // todo 정확한 타입 설정시 변경. id, profileImage...
    // 슬롯 비율로된 데이터
    val ratioSlotLayouts: List<SlotLayout> = emptyList(),
    val slotLayouts: List<CameraResult> = emptyList(),
) : UiState
