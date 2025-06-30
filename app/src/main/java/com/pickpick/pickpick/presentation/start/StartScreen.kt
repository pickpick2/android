package com.pickpick.pickpick.presentation.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.MainButton
import com.pickpick.pickpick.core.ui.theme.Black
import com.pickpick.pickpick.core.ui.theme.G100
import com.pickpick.pickpick.core.ui.theme.KakaoYellow
import com.pickpick.pickpick.core.ui.theme.clickableNoRipple
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body1Regular

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onNavigateToMain: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 56.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = modifier
                .size(180.dp),
            painter = painterResource(R.drawable.logo), contentDescription = "logo"
        )
        Spacer(modifier = modifier.height(88.dp))
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
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically
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
