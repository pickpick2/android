package com.pickpick.pickpick.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.G100
import com.pickpick.pickpick.presentation.main.component.IconWithTextButton

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            "로고",
            modifier = modifier
                .size(84.dp)
                .background(G100),
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            "사진",
            modifier = modifier
                .size(250.dp)
                .background(G100),
        )

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconWithTextButton(
                icon = painterResource(id = R.drawable.icon_camera),
                text = stringResource(R.string.go_to_camera),
                onClick = onCameraClick
            )

            IconWithTextButton(
                icon = painterResource(id = R.drawable.icon_gallery),
                text = stringResource(R.string.go_to_gallery),
                onClick = onGalleryClick
            )
        }

    }

}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen(onCameraClick = {}, onGalleryClick = {})
}