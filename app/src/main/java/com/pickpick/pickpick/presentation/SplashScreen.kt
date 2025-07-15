package com.pickpick.pickpick.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.PickPickTheme
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit = {},
) {
    LaunchedEffect(Unit) {
        onNavigateToLogin()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PrimaryDefault),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo), contentDescription = "splash"
        )
    }

}


@Preview(showBackground = true, heightDp = 800)
@Composable
fun FrameTestScreenPreview() {
    PickPickTheme {
        SplashScreen()
    }
}
