package com.pickpick.pickpick.presentation.album.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.Black
import com.pickpick.pickpick.core.ui.theme.Border
import com.pickpick.pickpick.core.ui.theme.White
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.DetailRegular

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    Box(
        modifier = modifier
            .height(38.dp)
            .fillMaxWidth()
            .background(color = White, shape = RoundedCornerShape(10.dp))
            .border(1.dp, Border, shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            BasicTextField(
                value = query,
                onValueChange = onQueryChanged,
                singleLine = true,
                textStyle = DetailRegular.copy(color = Black),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { onSearch() }),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                decorationBox = { innerTextField ->
                    if (query.isEmpty()) {
                        Text(
                            text = stringResource(R.string.album_search_placeholder),
                            style = DetailRegular.copy(color = Border)
                        )
                    }
                    innerTextField()
                }
            )

            Image(
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = "검색 아이콘"
            )
        }
    }
}
