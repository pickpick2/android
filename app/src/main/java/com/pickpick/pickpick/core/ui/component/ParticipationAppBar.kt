package com.pickpick.pickpick.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.pickpick.pickpick.R

@Composable
fun ParticipationAppBar(
    modifier: Modifier = Modifier, profileImages: List<String> = emptyList()
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = modifier.size(24.dp),
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo",
        )
        Box(
            modifier = modifier
                .widthIn(50.dp)
                .weight(1f)
        )
        ParticipationProfiles(
            modifier = modifier.weight(3f),
            profileImages = profileImages,
        )
    }
}


@Composable
fun ParticipationProfiles(
    modifier: Modifier = Modifier, size: Dp = 28.dp, profileImages: List<String> = emptyList()
) {
    Row(
        modifier = modifier.horizontalScroll(state = rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.End),
    ) {
        repeat(profileImages.size) { index ->
            Image(
                modifier = Modifier
                    .size(size)
                    .clip(shape = CircleShape),
                painter = rememberAsyncImagePainter(profileImages[index]),
                contentDescription = "profileImage"
            )
        }
    }
}
