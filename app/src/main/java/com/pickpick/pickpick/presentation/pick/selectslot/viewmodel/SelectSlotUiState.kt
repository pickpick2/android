package com.pickpick.pickpick.presentation.pick.selectslot.viewmodel

import com.pickpick.pickpick.core.presentation.UiState

data class SelectSlotUiState(
    // todo 정확한 타입 설정시 변경. id, profileImage...
    // 현재 선택된 슬롯 인덱스
    val selectedSlots: List<Int> = emptyList(),
) : UiState
