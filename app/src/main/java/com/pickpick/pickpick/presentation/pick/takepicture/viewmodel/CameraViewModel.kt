package com.pickpick.pickpick.presentation.pick.takepicture.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.lifecycle.viewModelScope
import com.pickpick.pickpick.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor() : BaseViewModel<CameraUiState>(
    CameraUiState()
) {

    fun capturePhoto(uri: Uri) {
        Log.d("CameraViewModel", "capturePhoto : ${uri}")
        Log.d("CameraViewModel", "capturePhoto toString: ${uri.toString()}")
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

    fun capturePhoto(context: Context) {
        viewModelScope.launch {

        }
    }
}
