package com.pickpick.pickpick.presentation.pick.takepicture.ui

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.core.resolutionselector.AspectRatioStrategy
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.io.File

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA, // 전면 카메라
    onCameraReady: (Camera?) -> Unit = {},
    onImageCaptureReady: (ImageCapture?) -> Unit = {}, // ✅ ImageCapture 콜백 추가
    onError: (Exception) -> Unit = {}
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycle = lifecycleOwner.lifecycle
    val context = LocalContext.current


    // 카메라 상태 추적
    var cameraProvider by remember { mutableStateOf<ProcessCameraProvider?>(null) }
    var camera by remember { mutableStateOf<Camera?>(null) }
    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) } // ✅ ImageCapture 상태 추가
    var isInitialized by remember { mutableStateOf(false) }

    // 생명주기 상태 관찰
    val lifecycleState by lifecycle.currentStateFlow.collectAsStateWithLifecycle()

    // 컴포넌트 해제 시 정리
    DisposableEffect(lifecycleOwner) {
        onDispose {
            cameraProvider?.unbindAll()
        }
    }

    AndroidView(
        modifier = modifier,

        factory = { ctx ->
            PreviewView(ctx).apply {
                this.scaleType = PreviewView.ScaleType.FILL_CENTER
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
            }

//        PreviewView(ctx).apply {
//            this.scaleType = scaleType
//            layoutParams = ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
//            )
//        }
        }, update = { previewView ->
            // 생명주기 상태에 따른 카메라 관리
            when (lifecycleState) {
                Lifecycle.State.STARTED, Lifecycle.State.RESUMED -> {
                    if (!isInitialized) {
                        initializeCamera(
                            context = context,
                            lifecycleOwner = lifecycleOwner,
                            previewView = previewView,
                            cameraSelector = cameraSelector,
                            onSuccess = { provider, cam, capture ->
                                cameraProvider = provider
                                camera = cam
                                imageCapture = capture
                                isInitialized = true
                                onCameraReady(cam)
                                onImageCaptureReady(capture)
                            },
                            onError = onError
                        )
                    }
                }

                Lifecycle.State.CREATED, Lifecycle.State.DESTROYED -> {
                    // 백그라운드로 갔을 때 리소스 정리
                    cameraProvider?.unbindAll()
                    camera = null
                    isInitialized = false
                    onCameraReady(null)
                    onImageCaptureReady(null)
                }

                else -> { /* INITIALIZED 상태 등 */
                }
            }
        }

    )
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun capturePhoto(
    imageCapture: ImageCapture,
    context: Context
): Uri? = withContext(Dispatchers.IO) {
    try {
        // 임시 파일 생성 (앱 캐시 디렉토리)
        val photoFile = File(
            context.cacheDir,
            "captured_image_${System.currentTimeMillis()}.jpg"
        )

        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        // 촬영 실행
        suspendCancellableCoroutine<Uri?> { continuation ->
            imageCapture.takePicture(
                outputFileOptions,
                ContextCompat.getMainExecutor(context),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                        val savedUri = Uri.fromFile(photoFile)
                        Log.d("CameraCapture", "사진 저장 성공: $savedUri")
                        continuation.resume(savedUri, {})
                    }

                    override fun onError(exception: ImageCaptureException) {
                        Log.e("CameraCapture", "사진 저장 실패", exception)
                        continuation.resume(null, {})
                    }
                }
            )
        }
    } catch (e: Exception) {
        Log.e("CameraCapture", "촬영 실패", e)
        null
    }
}


private fun initializeCamera(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    previewView: PreviewView,
    cameraSelector: CameraSelector,
    onSuccess: (ProcessCameraProvider, Camera, ImageCapture) -> Unit,
    onError: (Exception) -> Unit
) {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

    cameraProviderFuture.addListener({
        try {
            val provider = cameraProviderFuture.get()
            val resolutionSelector = ResolutionSelector.Builder()
                .setAspectRatioStrategy(AspectRatioStrategy.RATIO_4_3_FALLBACK_AUTO_STRATEGY)
                .setResolutionStrategy(ResolutionStrategy.HIGHEST_AVAILABLE_STRATEGY)
                .build()

            // Preview usecase 설정
            val preview = Preview.Builder()
                .setResolutionSelector(resolutionSelector)
                .build()
                .also {
                    it.surfaceProvider = previewView.surfaceProvider
                }

            // ImageCapture usecase 설정
            val imageCapture = ImageCapture.Builder()
                .setResolutionSelector(resolutionSelector)
                .setTargetRotation(previewView.display.rotation)
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                .build()

            provider.unbindAll()

            // Preview와 ImageCapture 모두 바인딩
            val camera = provider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageCapture
            )

            onSuccess(provider, camera, imageCapture)

        } catch (exc: Exception) {
            onError(exc)
        }
    }, ContextCompat.getMainExecutor(context))
}
