package com.pickpick.pickpick.presentation.album

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pickpick.pickpick.presentation.album.component.AlbumItem
import com.pickpick.pickpick.presentation.album.viewmodel.AlbumViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.BackLayout
import kotlin.collections.chunked
import kotlin.collections.forEach

@Composable
fun AlbumScreen(
    modifier: Modifier = Modifier,
    viewModel: AlbumViewModel = hiltViewModel(),
    onNavigateToMain: () -> Unit,
    onBackClick: () -> Unit
) {

    val items = listOf("사과", "바나나", "체리", "수박", "복숭아")

    BackLayout(
        title = stringResource(R.string.album_top_bar),
        onBackClick = onBackClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (rowItems in items.chunked(2)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowItems.forEach {
                        AlbumItem()
                    }
                }
            }
        }
    }

}