package com.pickpick.pickpick.presentation.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.MainButton
import com.pickpick.pickpick.core.ui.component.UnderlineTextField
import com.pickpick.pickpick.core.ui.theme.Border
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicFallback
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body1Regular
import com.pickpick.pickpick.presentation.info.component.InfoTitleContent
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

        InfoTitleContent()

        Spacer(modifier = Modifier.height(70.dp))

        Box(
            modifier = Modifier.size(150.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_default_profile),
                contentDescription = "프로필 이미지",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.icon_pencil),
                contentDescription = "프로필 수정",
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        UnderlineTextField(
            value = uiState.value.nickname, onValueChange = viewModel::updateNickname,
            placeholder = {
                Text(
                    text = stringResource(R.string.nickname_placeholder),
                    style = Body1Regular.copy(
                        color = Border,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp)
                )
            },
            textStyle = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = PyeojinGothicFallback,
                fontWeight = FontWeight.Medium
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            errorText = uiState.value.nicknameErrorText,
        )

        Spacer(modifier = Modifier.height(70.dp))

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