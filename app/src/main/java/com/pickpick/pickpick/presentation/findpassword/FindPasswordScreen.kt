package com.pickpick.pickpick.presentation.findpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.MainButton
import com.pickpick.pickpick.core.ui.component.UnderlineTextField
import com.pickpick.pickpick.core.ui.theme.Border
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.DetailRegular
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading1
import com.pickpick.pickpick.presentation.findpassword.viewmodel.FindPasswordViewModel

@Composable
fun FindPasswordScreen(
    modifier: Modifier = Modifier,
    viewModel: FindPasswordViewModel = hiltViewModel(),
    onNavigateToResetPW: () -> Unit,
    onNavigateToEmail: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 56.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Text(text = stringResource(R.string.find_password), style = Heading1.copy(PrimaryDefault))
            UnderlineTextField(
                value = uiState.email,
                onValueChange = viewModel::updateEmail,
                placeholder = {
                    Text(
                        stringResource(R.string.email_placeholder),
                        style = DetailRegular.copy(color = Border)
                    )
                },
                errorText = uiState.errorText
            )
            FindButton(
                onNavigateToResetPW = onNavigateToResetPW,
                onNavigateToEmail = onNavigateToEmail,
            )
        }
    }
}

@Composable
private fun FindButton(
    modifier: Modifier = Modifier,
    onNavigateToResetPW: () -> Unit,
    onNavigateToEmail: () -> Unit,
) {
    Column {
        MainButton(text = stringResource(R.string.next), onClick = onNavigateToResetPW)
        Spacer(modifier = modifier.height(15.dp))
        MainButton(text = stringResource(R.string.find_email), onClick = onNavigateToEmail)
    }
}


@Composable
@Preview(showBackground = true)
fun StartScreenPreview() {
    FindPasswordScreen(
        onNavigateToResetPW = {},
        onNavigateToEmail = {}
    )
}
