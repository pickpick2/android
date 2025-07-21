package com.pickpick.pickpick.presentation.auth.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.MainButton
import com.pickpick.pickpick.core.ui.theme.Black
import com.pickpick.pickpick.core.ui.theme.KakaoYellow
import com.pickpick.pickpick.core.ui.theme.clickableNoRipple
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body1Regular
import com.pickpick.pickpick.presentation.auth.start.viewmodel.AuthViewModel
import com.pickpick.pickpick.presentation.auth.start.viewmodel.GuestLoginResult


@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel<AuthViewModel>(),
    onNavigateToLogin: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onNavigateToMain: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        if (uiState.guestLoginResult is GuestLoginResult.Success) {
            val result = uiState.guestLoginResult as GuestLoginResult.Success
            // accessToken 이 있을 경우 메인 화면으로 이동
            result.accessToken?.let {
                onNavigateToMain()
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 56.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = modifier.size(180.dp),
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo"
        )
        Spacer(modifier = modifier.height(88.dp))
        // todo 현재 게스트 로그인만 구현하고 나중에 소셜이랑 사용자 로그인 추가하겠습니다.
        MainButton(
            text = "게스트 로그인",
            shape = RoundedCornerShape(5.dp),
            onClick = {
                if (uiState.guestLoginResult is GuestLoginResult.Success) {
                    val result = uiState.guestLoginResult as GuestLoginResult.Success
                    if (result.accessToken == null) viewModel.guestLogin()
                }
            },
        )
        Spacer(modifier = modifier.height(20.dp))
        MainButton(
            text = "로그인",
            shape = RoundedCornerShape(5.dp),
            onClick = onNavigateToLogin,
        )
        Spacer(modifier = modifier.height(20.dp))
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(40.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = KakaoYellow),
            shape = RoundedCornerShape(5.dp),
        ) {
            Row(
                modifier = modifier, verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.kakao),
                    contentDescription = "kakao",
                    tint = Black
                )
                Spacer(modifier.width(8.dp))
                Text(
                    "카카오 로그인", style = TextStyle(
                        color = Color.Black.copy(alpha = .85f),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 15.sp * 1.5,
                    )
                )
            }
        }
        Spacer(modifier = modifier.height(20.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(R.string.not_member_yet),
                style = Body1Regular.copy(color = Color(0xFF8B8B8B))
            )
            Text(
                modifier = modifier.clickableNoRipple(onNavigateToSignUp),
                text = stringResource(R.string.signup),
                style = Body1Regular.copy(color = Color(0xFF7621ED))
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun StartScreenPreview() {
    StartScreen(onNavigateToLogin = {}, onNavigateToSignUp = {}, onNavigateToMain = {})

}
