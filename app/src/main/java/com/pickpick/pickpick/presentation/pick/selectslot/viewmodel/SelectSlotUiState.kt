package com.pickpick.pickpick.presentation.pick.selectslot.viewmodel

import com.pickpick.pickpick.FrameLayout
import com.pickpick.pickpick.core.presentation.UiState

// todo API 완성 되면 domain 으로 옮기기
data class UserInfo(
    val id: Long = 0,
    val profileImage: String = "",
)

data class SlotLayout(
    val index: Int = 0,
    val x: Float = 0f,
    val y: Float = 0f,
    val width: Float = 0f,
    val height: Float = 0f,
    // 슬롯 선택한 사용자
    val selectUser: UserInfo? = null,
)

data class SelectSlotUiState(
    val frameLayout: FrameLayout = FrameLayout(),
    // todo 정확한 타입 설정시 변경. id, profileImage...
    // 슬롯 비율로된 데이터
    val ratioSlotLayouts: List<SlotLayout> = emptyList(),
    // 현재 선택된 슬롯 인덱스
    val slotLayouts: List<SlotLayout> = emptyList(),
) : UiState
