package com.pickpick.pickpick.core.ui.component.pickpick

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.pickpick.pickpick.R


@Composable
fun SelectedUser(modifier: Modifier = Modifier, imageUrl: String) {
    Box(modifier = modifier) {
        Image(
            modifier = modifier
                .size(30.dp)
                .clip(shape = CircleShape),
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null
        )
        Icon(
            modifier = modifier
                .align(alignment = Alignment.BottomEnd)
                .offset(
                    x = 5.dp, y = 5.dp
                ),
            painter = painterResource(R.drawable.ic_crown),
            contentDescription = "crown",
            tint = Color(0xFFF4D570)
        )
    }
}
