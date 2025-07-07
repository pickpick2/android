package com.pickpick.pickpick.core.ui.component.pickpick.slot

import android.net.Uri

sealed class SlotType {
    abstract val index: Int
    abstract val item: Int
    abstract val isSelected: Boolean

    data class Position(
        override val index: Int, override val item: Int, override val isSelected: Boolean
    ) : SlotType()

    data class Camera(
        override val index: Int,
        override val item: Int,
        override val isSelected: Boolean,
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
