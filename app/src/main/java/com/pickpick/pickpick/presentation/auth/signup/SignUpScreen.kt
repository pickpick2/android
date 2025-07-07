package com.pickpick.pickpick.presentation.auth.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pickpick.pickpick.R
import com.pickpick.pickpick.presentation.auth.signup.component.SignUpForm
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpick.pickpick.core.ui.component.MainButton
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.clickableNoRipple
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body2Regular
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading1
import com.pickpick.pickpick.presentation.auth.signup.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    onNavigateToPolicy: () -> Unit,
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
        Text(text = stringResource(R.string.signup), style = Heading1.copy(PrimaryDefault))
        Spacer(modifier = modifier.height(40.dp))
        SignUpForm(
            uiState = uiState.value,
            updateEmail = viewModel::updateEmail,
            updatePassword = viewModel::updatePassword,
            updateCheckPassword = viewModel::updateCheckPassword,
            signUp = viewModel::signUp
        )
        Spacer(modifier = modifier.height(30.5.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickableNoRipple(onNavigateToPolicy),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            RadioButton(onClick = null, selected = uiState.value.checkPolicy)
            Spacer(modifier = modifier.width(10.dp))
            Text(text = stringResource(R.string.privacy_policy_check), style = Body2Regular)
        }
        Spacer(modifier = modifier.height(60.dp))
        MainButton(
            text = stringResource(R.string.check),
            enabled = uiState.value.enabled,
            onClick = viewModel::signUp
        )
    }
}

@Composable
@Preview(showBackground = true)
fun StartScreenPreview() {
    SignUpScreen(onNavigateToPolicy = {}, onNavigateToComplete = {})
}
