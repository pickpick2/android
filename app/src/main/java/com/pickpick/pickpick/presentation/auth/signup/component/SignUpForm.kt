package com.pickpick.pickpick.presentation.auth.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.UnderlineTextField
import com.pickpick.pickpick.core.ui.theme.Border
import com.pickpick.pickpick.core.ui.theme.clickableNoRipple
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.DetailRegular
import com.pickpick.pickpick.presentation.auth.signup.viewmodel.SignUpUiState

@Composable
fun SignUpForm(
    modifier: Modifier = Modifier,
    uiState: SignUpUiState,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
    updateCheckPassword: (String) -> Unit,
    signUp: () -> Unit,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isCheckPasswordVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(30.dp)) {
        UnderlineTextField(
            value = uiState.email, onValueChange = updateEmail,
            placeholder = {
                Text(
                    text = stringResource(R.string.email_placeholder),
                    style = DetailRegular.copy(color = Border)
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            errorText = uiState.emailErrorText,
        )
        UnderlineTextField(
            value = uiState.password,
            onValueChange = updatePassword,
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
            onValueChange = updateCheckPassword,
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
            keyboardActions = KeyboardActions(onDone = { signUp() }),
            errorText = uiState.checkPasswordErrorText
        )
    }

}

@Composable
@Preview(showBackground = true)
fun SignUpFormPreview() {
    SignUpForm(
        uiState = SignUpUiState(),
        updateEmail = {},
        updatePassword = {},
        updateCheckPassword = {},
        signUp = {})
}
