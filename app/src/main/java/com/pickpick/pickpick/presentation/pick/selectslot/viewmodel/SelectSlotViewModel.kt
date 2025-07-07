package com.pickpick.pickpick.presentation.pick.selectslot.viewmodel

import com.pickpick.pickpick.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SelectSlotViewModel @Inject constructor() :
    BaseViewModel<SelectSlotUiState>(SelectSlotUiState()) {

    fun selectSlot(slotIndex: Int) {
        _uiState.update {
            it.copy(
                selectedSlots = it.selectedSlots.toMutableList().apply {
                    if (contains(slotIndex)) {
                        remove(slotIndex)
                    } else {
                        add(slotIndex)
                    }
                },
            )
        }
    }
}
