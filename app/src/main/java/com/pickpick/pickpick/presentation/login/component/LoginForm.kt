package com.pickpick.pickpick.presentation.login.component

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.UnderlineTextField
import com.pickpick.pickpick.core.ui.theme.Black
import com.pickpick.pickpick.core.ui.theme.Border
import com.pickpick.pickpick.core.ui.theme.clickableNoRipple
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.DetailRegular
import com.pickpick.pickpick.presentation.login.viewmodel.LoginUiState
import kotlin.Boolean

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
    login: () -> Unit
) {

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    Column {
        UnderlineTextField(
            value = uiState.email, onValueChange = updateEmail, placeholder = {
                Text(
                    stringResource(R.string.email_placeholder),
                    style = DetailRegular.copy(color = Border)
                )
            }, singleLine = true, keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            )
        )
        Spacer(modifier = Modifier.height(34.dp))
        UnderlineTextField(
            value = uiState.password,
            onValueChange = updatePassword,
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
            placeholder = {
                Text(
                    stringResource(R.string.password_placeholder),
                    style = DetailRegular.copy(color = Border)
                )
            },
            singleLine = true,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardActions = KeyboardActions(
                onDone = {
                    Log.d("LoginForm", "Login Button Clicked")
                    login()
                },
            ),
        )
    }
}


@Composable
@Preview(showBackground = true)
fun LoginFormPreview() {
    LoginForm(uiState = LoginUiState(), updateEmail = {}, updatePassword = {}, login = {})
}
