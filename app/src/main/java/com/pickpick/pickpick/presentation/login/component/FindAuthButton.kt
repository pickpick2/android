package com.pickpick.pickpick.presentation.login.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.clickableNoRipple
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body1Regular

@Composable
fun FindAuthButton(
    modifier: Modifier = Modifier, onNavigateToEmail: () -> Unit, onNavigateToPassword: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
    ) {
        FindButton(
            text = stringResource(R.string.find_email),
            onClick = onNavigateToEmail,
        )
        Text(
            text = " | ", style = Body1Regular.copy(color = Color(0xFF8B8B8B))
        )
        FindButton(
            text = stringResource(R.string.find_password),
            onClick = onNavigateToPassword,
        )
    }
}

@Composable
private fun FindButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Text(
        text = text,
        style = Body1Regular.copy(color = Color(0xFF8B8B8B)),
        modifier = modifier.clickableNoRipple(onClick),
    )
}

@Composable
@Preview(showBackground = true)
fun FindAuthButtonPreview() {
    FindAuthButton(onNavigateToEmail = {}, onNavigateToPassword = {})
}
