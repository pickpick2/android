package com.pickpick.pickpick.presentation.album

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
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
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.BackLayout
import com.pickpick.pickpick.presentation.album.component.SearchBar
import com.pickpick.pickpick.presentation.album.component.SortDropdown
import kotlin.collections.chunked
import kotlin.collections.forEach

@Composable
fun AlbumScreen(
    modifier: Modifier = Modifier,
    viewModel: AlbumViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    val items = listOf("사과", "바나나", "체리", "수박", "복숭아")

    BackLayout(
        title = stringResource(R.string.album_top_bar),
        onBackClick = onBackClick
    ) {
        Column(
            modifier = Modifier
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
                        // TODO: 검색
                    }
                )

                Spacer(modifier = Modifier.width(10.dp))

                // 정렬 드롭다운
                SortDropdown(
                    modifier = Modifier.weight(1f),
                    selectedOption = selectedSort,
                    options = sortOptions,
                    onOptionSelected = { selectedSort = it }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
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

}

@Composable
@Preview(showBackground = true)
fun AlbumScreenPreview() {
    AlbumScreen (onBackClick = {})
}