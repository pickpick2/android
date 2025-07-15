package com.pickpick.pickpick.presentation.pick.room

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.presentation.pick.room.component.RoomMemberItem
import androidx.compose.ui.res.stringResource
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.BackLayout
import com.pickpick.pickpick.core.ui.component.IconBorderButton
import com.pickpick.pickpick.core.ui.component.MainButton

@Composable
fun ReadyScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onNavigatePickStart: () -> Unit
) {

    val items = listOf("테스트", "1", "2")

    BackLayout(
        title = stringResource(R.string.ready_room_top_bar),
        onBackClick = onBackClick
    ) {

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                for (item in items) {
                    RoomMemberItem()
                }
            }

            Spacer(modifier = Modifier.height(70.dp))

            MainButton(
                text = stringResource(R.string.start),
                enabled = true,
                onClick = onNavigatePickStart
            )

            Spacer(modifier = Modifier.height(10.dp))

            IconBorderButton(
                text = stringResource(R.string.invite),
                onClick = {}
            )
        }
    }
}