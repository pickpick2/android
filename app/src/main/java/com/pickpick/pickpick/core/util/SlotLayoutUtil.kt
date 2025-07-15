package com.pickpick.pickpick.core.util

import androidx.compose.ui.unit.Density
import com.pickpick.pickpick.FrameLayout
import com.pickpick.pickpick.presentation.pick.selectslot.viewmodel.SlotLayout

/**
 * 프레임의 슬롯 레이아웃 관련 유틸
 */
object SlotLayoutUtil {

    /**
     * 프레임 정보를 받아서 슬롯 레이아웃 정보를 반환
     * 슬롯 정보: 비율 -> dp 변환
     */
    fun getSlotLayoutInfo(
        density: Density,
        frameLayout: FrameLayout,
        slotLayouts: List<SlotLayout>
    ): List<SlotLayout> {
        return slotLayouts.map { slotInfo ->
            with(density) {
                // 이미지 크기를 px -> dp 로 변환
                val imageWidth = frameLayout.width.toDp()
                val imageHeight = frameLayout.height.toDp()

                // 이미지 위치를 px -> dp 로 변환
                val imageX = frameLayout.x.toDp()
                val imageY = frameLayout.y.toDp()

                // 슬롯 위치
                // 이미지 위치 + (이미지 크기 * 슬롯 위치(비율))
                val slotX = imageX + (imageWidth * slotInfo.x / 100)
                val slotY = imageY + (imageHeight * slotInfo.y / 100)


                // 슬롯 크기
                // 이미지 크기 * 슬롯 크기(비율)
                val slotWidth = imageWidth * slotInfo.width / 100
                val slotHeight = imageHeight * slotInfo.height / 100

                slotInfo.copy(
                    x = slotX.value,
                    y = slotY.value,
                    width = slotWidth.value,
                    height = slotHeight.value,
                )
            }
        }
    }
}
