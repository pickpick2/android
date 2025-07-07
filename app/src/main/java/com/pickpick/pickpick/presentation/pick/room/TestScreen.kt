package com.pickpick.pickpick.presentation.pick.room

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import java.util.UUID
import kotlin.math.roundToInt

sealed class StickerState {
    abstract val id: String
    abstract var offset: Offset
    abstract var scale: Float

    data class ImageSticker(
        override val id: String = UUID.randomUUID().toString(),
        val resId: Int,
        override var offset: Offset = Offset.Zero,
        override var scale: Float = 1f
    ) : StickerState()

    data class TextSticker(
        override val id: String = UUID.randomUUID().toString(),
        var text: String,
        override var offset: Offset = Offset.Zero,
        override var scale: Float = 1f
    ) : StickerState()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(
    modifier: Modifier = Modifier
) {
    val stickerList = remember { mutableStateListOf<StickerState>() }
    val selectedId = remember { mutableStateOf<String?>(null) }
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // 텍스트 입력 및 추가 버튼
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { Text("텍스트 입력") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (inputText.isNotBlank()) {
                    val newTextSticker = StickerState.TextSticker(text = inputText)
                    stickerList.add(newTextSticker)
                    selectedId.value = newTextSticker.id
                    inputText = ""
                }
            }) {
                Text("텍스트 추가")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 이미지 스티커 선택 영역
        val sampleStickers = listOf(
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background,
            R.drawable.icon_crown
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sampleStickers) { resId ->
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(MaterialTheme.shapes.small)
                        .pointerInput(Unit) {
                            detectTapGestures {
                                val newSticker = StickerState.ImageSticker(resId = resId)
                                stickerList.add(newSticker)
                                selectedId.value = newSticker.id
                            }
                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 스티커가 배치되는 화면
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            stickerList.forEach { sticker ->
                val isSelected = selectedId.value == sticker.id

                StickerLayer(
                    sticker = sticker,
                    isSelected = isSelected,
                    onSelect = {
                        selectedId.value = sticker.id
                    }
                )
            }
        }
    }
}

@Composable
fun StickerLayer(
    sticker: StickerState,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    var offset by remember { mutableStateOf(sticker.offset) }
    var scale by remember { mutableFloatStateOf(sticker.scale) }

    Box(
        modifier = Modifier
            .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .pointerInput(Unit) {
                detectTapGestures(onTap = { onSelect() })
            }
            .then(
                if (isSelected) Modifier.pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        offset += pan
                        scale *= zoom
                        sticker.offset = offset
                        sticker.scale = scale
                    }
                } else Modifier
            )
    ) {
        when (sticker) {
            is StickerState.ImageSticker -> {
                Image(
                    painter = painterResource(id = sticker.resId),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(100.dp)
                        .border(
                            width = if (isSelected) 2.dp else 0.dp,
                            color = if (isSelected) PrimaryDefault else Color.Transparent
                        )
                )
            }

            is StickerState.TextSticker -> {
                Text(
                    text = sticker.text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .border(
                            width = if (isSelected) 2.dp else 0.dp,
                            color = if (isSelected) PrimaryDefault else Color.Transparent
                        )
                        .padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestScreenPreview() {
    TestScreen()
}
