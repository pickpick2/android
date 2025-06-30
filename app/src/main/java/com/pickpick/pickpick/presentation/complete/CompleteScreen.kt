package com.pickpick.pickpick.presentation.complete

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.core.ui.component.MainButton
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading1
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading3

@Composable
fun CompleteScreen(
    modifier: Modifier = Modifier, onNavigateToLogin: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 86.dp),
        verticalArrangement = Arrangement.spacedBy(52.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("비밀번호 변경 완료!", style = Heading1)
            Spacer(modifier = modifier.height(10.dp))
            Text("새로운 비밀번호로 로그인 해요", style = Heading3.copy(fontWeight = FontWeight.Medium))
        }
        MainButton(
            text = "확인",
            onClick = onNavigateToLogin,
        )

    }
}
