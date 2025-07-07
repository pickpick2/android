package com.pickpick.pickpick.presentation.auth.info.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body1Regular
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading1

@Composable
fun InfoTitleContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "반가워요!",
            style = Heading1
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_text_loco),
                contentDescription = "Pic With Me 로고"
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "에서 활동하실",
                style = Body1Regular
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = PrimaryDefault)) {
                    append("닉네임")
                }
                append("과 ")
                withStyle(style = SpanStyle(color = PrimaryDefault)) {
                    append("프로필 사진")
                }
                append("을 등록해주세요.")
            },
            style = Body1Regular
        )
    }
}
