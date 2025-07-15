package com.pickpick.pickpick.core.ui.component.pickpick.slot

import android.net.Uri
import com.pickpick.pickpick.presentation.pick.selectslot.viewmodel.SlotLayout

sealed class SlotType {
    abstract val slotLayout: SlotLayout

    val isSelected: Boolean
        get() = slotLayout.selectUser != null

    /**
    프레임 기준 x좌표가 절반보다 왼쪽에 있는지
     */
    val isLeftPosition: Boolean
        get() = slotLayout.x < 50f


    /**
     * 위치 슬롯
     */
    data class Position(
        override val slotLayout: SlotLayout,
    ) : SlotType()

    /**
     * 카메라 슬롯
     */
    data class Camera(
        override val slotLayout: SlotLayout,
        val uri: Uri?,
    ) : SlotType()

//    data class CaptureResult(
//        override val index: Int,
//        override val item: Int,
//        override val isSelected: Boolean,
//        val uri: Uri?,
//    ) : SlotType()
}


sealed class SlotAction {
    data class SelectPosition(val index: Int) : SlotAction()
    data class CapturePhoto(val uri: Uri) : SlotAction()
}
