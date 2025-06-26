package com.pickpick.pickpick.presentation.album

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.pickpick.pickpick.presentation.album.viewmodel.AlbumViewModel

@Composable
fun AlbumScreen(
    modifier: Modifier = Modifier,
    viewModel: AlbumViewModel = hiltViewModel(),
    onNavigateToMain: () -> Unit,
) {

}