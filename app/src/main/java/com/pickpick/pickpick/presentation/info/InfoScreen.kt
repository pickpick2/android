package com.pickpick.pickpick.presentation.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.MainButton
import com.pickpick.pickpick.core.ui.component.UnderlineTextField
import com.pickpick.pickpick.core.ui.theme.Border
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.DetailRegular
import com.pickpick.pickpick.presentation.info.viewmodel.InfoViewModel

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    viewModel: InfoViewModel = hiltViewModel(),
    onNavigateToComplete: () -> Unit,
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 56.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        UnderlineTextField(
            value = uiState.value.nickname, onValueChange = viewModel::updateNickname,
            placeholder = {
                Text(
                    text = stringResource(R.string.nickname_placeholder),
                    style = DetailRegular.copy(color = Border)
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            errorText = uiState.value.nicknameErrorText,
        )

        MainButton(
            text = stringResource(R.string.signup_complete),
            enabled = uiState.value.enabled,
            onClick = viewModel::complete
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InfoScreenPreview() {
    InfoScreen(onNavigateToComplete = {})
}