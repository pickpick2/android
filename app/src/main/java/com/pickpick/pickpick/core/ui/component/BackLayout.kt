package com.pickpick.pickpick.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pickpick.pickpick.R

@Composable
fun BackLayout(
    modifier: Modifier = Modifier,
    title: String? = null,
    onBackClick: (() -> Unit),
    content: @Composable () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        CommonBackTopBar(
            title = title,
            onBackClick = onBackClick
        )

        content()
    }
}