package com.pickpick.pickpick.presentation.policy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.Black
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.White
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body1Regular
import com.pickpick.pickpick.presentation.signup.viewmodel.SignUpViewModel

@Composable
fun PolicyScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    onNavigateToSignUp: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(modifier = modifier
            .weight(1f)
            .padding(horizontal = 30.dp)) {
            Spacer(modifier = modifier.height(100.dp))
            Text(
                text = stringResource(R.string.privacy_policy), style = TextStyle(
                    fontSize = 24.sp, lineHeight = 32.sp, fontWeight = FontWeight.SemiBold
                    , color = Black
                )
            )
            Spacer(modifier = modifier.height(20.dp))

            Text(
                text = stringResource(R.string.privacy_policy_detail1), style = TextStyle(
                    fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.Normal
                )
            )
            Spacer(modifier = modifier.height(40.dp))

            Text(
                text = stringResource(R.string.privacy_policy_detail2), style = TextStyle(
                    fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.Normal
                )
            )
        }

        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryDefault),
            onClick = {
                viewModel.updateCheckPolicy(true)
                onNavigateToSignUp()
            }) {
            Text("위 사항을 읽고 동의합니다.", style = Body1Regular.copy(color = White))
        }

    }
}

@Composable
@Preview(showBackground = true)
fun PolicyScreenPreview() {
    PolicyScreen(
        onNavigateToSignUp = {}
    )
}
