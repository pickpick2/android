package com.pickpick.pickpick.presentation.room

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.presentation.room.component.RoomMemberItem
import java.lang.reflect.Modifier
import androidx.compose.ui.res.stringResource
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.MainButton

@Composable
fun ReadyScreen(
    modifier: Modifier
) {

    val items = listOf("테스트")

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