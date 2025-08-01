package com.pickpick.pickpick.presentation.album.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.clickableNoRipple
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicFallback
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.DetailBold
import com.pickpick.pickpick.domain.album.model.Photo

@Composable
fun AlbumItem(
    modifier: Modifier = Modifier,
    photo: Photo = Photo(),
    onDeleteClick: () -> Unit
) {

    Column(
        modifier = modifier
            .width(168.dp)
    ) {
        AsyncImage(
            model = photo.url,
            contentDescription = "사진",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(210.dp)
                .width(168.dp)
        )

        Column(
            modifier = Modifier.padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = photo.title,
                    style = DetailBold
                )

                Spacer(modifier = Modifier.width(4.dp))

                // 앨범 삭제
                Image(
                    painter = painterResource(id = R.drawable.icon_trash),
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                        .clickableNoRipple(onDeleteClick)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_time),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )

                Text(
                    text = photo.createdAt,
                    fontFamily = PyeojinGothicFallback,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp
                )
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun AlbumItemPreview() {
    AlbumItem(onDeleteClick = {})
}