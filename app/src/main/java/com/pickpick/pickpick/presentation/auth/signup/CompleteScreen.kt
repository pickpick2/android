package com.pickpick.pickpick.presentation.auth.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.MainButton
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body1Regular
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading1

@Composable
fun CompleteScreen(
    modifier: Modifier = Modifier,
    onNavigateToMain: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "회원가입 완료",
            style = Heading1
        )

        Text(
            text = "친구들과 함께 사진찍어 볼까요?",
            style = Body1Regular
        )

        Spacer(modifier = Modifier.height(50.dp))

        MainButton(
            text = stringResource(R.string.check),
            onClick = onNavigateToMain
        )

    }

}