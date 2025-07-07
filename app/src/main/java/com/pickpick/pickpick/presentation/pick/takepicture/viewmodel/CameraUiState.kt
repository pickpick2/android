package com.pickpick.pickpick.presentation.pick.takepicture.viewmodel

import androidx.camera.core.ImageCapture
import com.pickpick.pickpick.core.presentation.UiState

data class CameraUiState(
    val capturedPhotoUri: String? = null,
    val selectedSlots: List<Int> = emptyList(),
    val imageCapture: ImageCapture? = null,
) : UiState
