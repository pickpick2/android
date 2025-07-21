package com.pickpick.pickpick.data.pick.remote.dto

import com.pickpick.pickpick.domain.pick.model.Room

data class RoomResponse(
    val roomId: Int = 0,
)

fun RoomResponse.toDomain(): Room {
    return Room(
        roomId = this.roomId
    )
}
