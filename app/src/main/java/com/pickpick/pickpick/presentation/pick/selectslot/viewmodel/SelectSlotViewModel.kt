package com.pickpick.pickpick.presentation.pick.selectslot.viewmodel

import com.pickpick.pickpick.FrameLayout
import com.pickpick.pickpick.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SelectSlotViewModel @Inject constructor() :
    BaseViewModel<SelectSlotUiState>(SelectSlotUiState()) {

    init {
        _uiState.update {
            it.copy(ratioSlotLayouts = getSlotInfos())
        }
    }

    // 값이 변경되었을 때만 업데이트
    fun setFrameLayout(frameLayout: FrameLayout) {
        if (_uiState.value.frameLayout != frameLayout) {
            _uiState.update {
                it.copy(frameLayout = frameLayout)
            }
        }
    }

    fun setSlotLayouts(slotLayouts: List<SlotLayout>) {
        // 이전 값과 다를 때만 업데이트
        if (_uiState.value.slotLayouts != slotLayouts) {
            _uiState.update {
                it.copy(slotLayouts = slotLayouts)
            }
        }
    }

    fun selectSlot(slotIndex: Int, userInfo: UserInfo) {
        _uiState.update { currentState ->
            val updatedSlotLayouts = currentState.slotLayouts.mapIndexed { index, slotLayout ->
                when {
                    // 선택된 슬롯인 경우
                    index == slotIndex -> {
                        if (slotLayout.selectUser == null) {
                            slotLayout.copy(selectUser = userInfo)
                        } else {
                            slotLayout.copy(selectUser = null) // 선택 해제
                        }
                    }
                    // 다른 슬롯에서 같은 유저 선택 해제 (한 명당 하나의 슬롯만)
                    slotLayout.selectUser?.id == userInfo.id -> {
                        slotLayout.copy(selectUser = null)
                    }

                    else -> slotLayout
                }
            }

            currentState.copy(slotLayouts = updatedSlotLayouts)
        }
    }
}


// todo dummy data 나중에 삭제 예정
private fun getSlotInfos(): List<SlotLayout> = listOf(
    SlotLayout(
        index = 0, x = 5.0f,       // left
        y = 23.3f,      // top
        width = 45.0f, height = 36.1f
    ), SlotLayout(
        index = 1, x = 51.67f,     // left
        y = 3.3f,       // top
        width = 45.0f, height = 36.1f
    ), SlotLayout(
        index = 2, x = 5.0f,       // left
        y = 60.6f,      // top
        width = 45.0f, height = 36.1f
    ), SlotLayout(
        index = 3, x = 51.7f,      // left
        y = 40.6f,      // top
        width = 45.0f, height = 36.1f
    )
)
