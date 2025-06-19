package com.pickpick.pickpick.presentation.login

import android.opengl.Visibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.MainButton
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading1
import com.pickpick.pickpick.presentation.login.component.FindAuthButton
import com.pickpick.pickpick.presentation.login.component.LoginForm
import com.pickpick.pickpick.presentation.login.viewmodel.LoginViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpick.pickpick.core.ui.theme.Pink
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.DetailRegular
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToMain:() -> Unit,
    onNavigateToEmail: () -> Unit,
    onNavigateToPassword: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 56.dp)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = stringResource(R.string.login), style = Heading1.copy(color = PrimaryDefault))

        LoginForm(
            uiState = uiState.value,
            updateEmail = viewModel::updateEmail,
            updatePassword = viewModel::updatePassword,
            login = viewModel::login
        )

        MainButton(
            text = stringResource(R.string.login),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            enabled = uiState.value.enabled,
            onClick = viewModel::login,
        )

        FindAuthButton(
            onNavigateToEmail = onNavigateToEmail,
            onNavigateToPassword = onNavigateToPassword,
        )

        if (uiState.value.errorText != null) Text(
            text = uiState.value.errorText!!,
            style = DetailRegular.copy(color = Pink)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen(
        onNavigateToEmail = {},
        onNavigateToPassword = {},
        onNavigateToMain = {}
    )
}
