package com.pickpick.pickpick.presentation.pick.takepicture.viewmodel

import android.net.Uri
import android.util.Log
import androidx.camera.core.ImageCapture
import com.pickpick.pickpick.core.presentation.BaseViewModel
import com.pickpick.pickpick.core.ui.component.pickpick.slot.SlotType
import com.pickpick.pickpick.domain.pick.model.FrameLayout
import com.pickpick.pickpick.presentation.pick.selectslot.viewmodel.SlotLayout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class CameraViewModel @Inject constructor() : BaseViewModel<CameraUiState>(
    CameraUiState()
) {

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

    fun setSlotLayouts(slotLayouts: List<SlotType>) {
        // 이전 값과 다를 때만 업데이트
        if (_uiState.value.slotLayouts != slotLayouts) {
            _uiState.update {
                it.copy(slotLayouts = slotLayouts as List<SlotType.Camera>)
            }
        }
    }


    fun capturePhoto(uri: Uri) {
        Log.d("CameraViewModel", "capturePhoto : ${uri}")
        Log.d("CameraViewModel", "capturePhoto toString: ${uri.toString()}")

        // todo 수정 예정
        _uiState.update {
            it.copy(
                capturedPhotoUri = uri.toString()
            )
        }
    }

    fun setImageCapture(imageCapture: ImageCapture) {
        _uiState.update {
            it.copy(
                imageCapture = imageCapture
            )
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
