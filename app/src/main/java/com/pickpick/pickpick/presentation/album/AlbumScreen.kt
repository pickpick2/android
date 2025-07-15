package com.pickpick.pickpick.presentation.album

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pickpick.pickpick.presentation.album.component.AlbumItem
import com.pickpick.pickpick.presentation.album.viewmodel.AlbumViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.BackLayout
import com.pickpick.pickpick.presentation.album.component.SearchBar
import com.pickpick.pickpick.presentation.album.component.CustomDropdown
import com.pickpick.pickpick.presentation.album.component.DeletePhotoDialog

@Composable
fun AlbumScreen(
    modifier: Modifier = Modifier,
    viewModel: AlbumViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()
    val albums = uiState.pagedPhotos.collectAsLazyPagingItems()

    var showDialog by remember { mutableStateOf(false) }
    var selectedPhotoId by remember { mutableStateOf<String>("") }

    if (showDialog && selectedPhotoId.isNotEmpty()) {
        DeletePhotoDialog(
            onDismiss = {
                showDialog = false
                selectedPhotoId = ""
            },
            onDelete = {
                viewModel.removePhoto(selectedPhotoId)
                showDialog = false
                selectedPhotoId = ""
            }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.loadPagedPhotos()
    }

    BackLayout(
        title = stringResource(R.string.album_top_bar),
        onBackClick = onBackClick
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            var query by remember { mutableStateOf("") }
            var selectedSort by remember { mutableStateOf("오래된순") }
            val sortOptions = listOf("오래된순", "최신순", "제목순")

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 검색창
                SearchBar(
                    modifier = Modifier.weight(2f),
                    query = query,
                    onQueryChanged = { query = it },
                    onSearch = {
                        viewModel.loadPagedPhotos(query = query)
                    }
                )

                Spacer(modifier = Modifier.width(10.dp))

                // 정렬 드롭다운
                CustomDropdown(
                    modifier = Modifier.weight(1f),
                    selectedOption = selectedSort,
                    options = sortOptions,
                    onOptionSelected = {
                        selectedSort = it
                        viewModel.loadPagedPhotos(cursor = selectedSort)
                    }
                )
            }

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                columns = GridCells.Fixed(2),
                content = {
                    items(albums.itemCount) { index ->
                        albums[index]?.let { photo ->
                            AlbumItem(
                                photo = photo,
                                onDeleteClick = {
                                    selectedPhotoId = photo.photoId
                                    showDialog = true
                                }
                            )
                        }
                    }

                    when (val append = albums.loadState.append) {
                        is LoadState.Loading -> item { CircularProgressIndicator(modifier = Modifier.fillMaxWidth()) }
                        is LoadState.Error -> item { Text("Error: ${append.error.message}") }
                        else -> {}
                    }
                }
            )

        }
    }

}

@Composable
@Preview(showBackground = true)
fun AlbumScreenPreview() {
    AlbumScreen (onBackClick = {})
}