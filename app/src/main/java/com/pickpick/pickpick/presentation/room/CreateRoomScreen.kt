package com.pickpick.pickpick.presentation.room

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.MainButton
import com.pickpick.pickpick.core.ui.theme.Border
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body1Regular

@Composable
fun CreateChatRoom(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(R.string.create_room_title),
            style = Body1Regular.copy(
                color = Border,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(70.dp))

        // TODO: 방 제목, 인원 선택 추가

        Spacer(modifier = Modifier.height(70.dp))

        MainButton(
            text = stringResource(R.string.create_room),
            enabled = true,
            onClick = {}
        )
    }

}