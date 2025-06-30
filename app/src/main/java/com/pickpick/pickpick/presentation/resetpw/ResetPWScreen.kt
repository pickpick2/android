package com.pickpick.pickpick.presentation.resetpw

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.MainButton
import com.pickpick.pickpick.core.ui.component.UnderlineTextField
import com.pickpick.pickpick.core.ui.theme.Border
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.clickableNoRipple
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.DetailRegular
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading1
import com.pickpick.pickpick.presentation.resetpw.viewmodel.ResetPWViewModel

@Composable
fun ResetPWScreen(
    modifier: Modifier = Modifier,
    viewModel: ResetPWViewModel = hiltViewModel(),
    onNavigateToComplete: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isCheckPasswordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 56.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = stringResource(R.string.reset_password),
            style = Heading1.copy(color = PrimaryDefault)
        )

        UnderlineTextField(
            value = uiState.password,
            onValueChange = viewModel::updatePassword,
            placeholder = {
                Text(
                    text = stringResource(R.string.password_placeholder),
                    style = DetailRegular.copy(color = Border)
                )
            },
            suffix = {
                Icon(
                    modifier = modifier
                        .size(20.dp)
                        .clickableNoRipple {
                            isPasswordVisible = !isPasswordVisible
                        },
                    painter = if (isPasswordVisible) painterResource(R.drawable.visible) else painterResource(
                        R.drawable.invisible
                    ),
                    contentDescription = if (isPasswordVisible) "visible" else "invisible",
                    tint = Border,
                )
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            errorText = uiState.passwordErrorText
        )

        UnderlineTextField(
            value = uiState.checkPassword,
            onValueChange = viewModel::updateCheckPassword,
            placeholder = {
                Text(
                    text = stringResource(R.string.password_confirm_placeholder),
                    style = DetailRegular.copy(color = Border)
                )
            },
            suffix = {
                Icon(
                    modifier = modifier
                        .size(20.dp)
                        .clickableNoRipple {
                            isCheckPasswordVisible = !isCheckPasswordVisible
                        },
                    painter = if (isCheckPasswordVisible) painterResource(R.drawable.visible) else painterResource(
                        R.drawable.invisible
                    ),
                    contentDescription = if (isCheckPasswordVisible) "visible" else "invisible",
                    tint = Border,
                )
            },
            visualTransformation = if (isCheckPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardActions = KeyboardActions(onDone = { viewModel.resetPassword() }),
            errorText = uiState.checkPasswordErrorText
        )
        MainButton(
            text = stringResource(R.string.complete),
            enabled = uiState.enabled,
            onClick = viewModel::resetPassword
        )

    }
}
