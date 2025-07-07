package com.pickpick.pickpick.presentation.pick.room

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.presentation.pick.room.component.RoomMemberItem
import androidx.compose.ui.res.stringResource
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.BackLayout
import com.pickpick.pickpick.core.ui.component.MainButton

@Composable
fun ReadyScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {

    val items = listOf("테스트")

    BackLayout(
        title = stringResource(R.string.ready_room_top_bar),
        onBackClick = onBackClick
    ) {

        Column {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                for (item in items) {
                    RoomMemberItem()
                }
            }

            MainButton(
                text = stringResource(R.string.start),
                enabled = true,
                onClick = {}
            )

            MainButton(
                text = stringResource(R.string.invite),
                enabled = true,
                onClick = {}
            )
        }
    }
}