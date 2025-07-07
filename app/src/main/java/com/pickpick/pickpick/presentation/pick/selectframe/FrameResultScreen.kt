package com.pickpick.pickpick.presentation.pick.selectframe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body2Regular
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading1
import com.pickpick.pickpick.presentation.pick.room.viewmodel.RoomViewModel

@Composable
fun FrameResultScreen(
    modifier: Modifier = Modifier,
    viewModel: RoomViewModel = hiltViewModel(),
    onNavigateToNext: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .imePadding(),
    ) {

        Text(
            text = stringResource(R.string.frame_title),
            style = Heading1
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_time_purple),
                contentDescription = null,
                modifier = Modifier.size(12.dp)
            )

            Text(
                text = stringResource(R.string.frame_notice),
                style = Body2Regular.copy(color = PrimaryDefault)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

    }
}

@Composable
@Preview(showBackground = true)
fun FramePreview() {
    FrameScreen(onNavigateToNext = {})
}