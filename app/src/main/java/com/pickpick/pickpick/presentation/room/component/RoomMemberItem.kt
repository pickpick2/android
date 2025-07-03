package com.pickpick.pickpick.presentation.room.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.PrimaryLight
import com.pickpick.pickpick.core.ui.theme.White
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicFallback
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body2Bold

@Composable
fun RoomMemberItem(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 4.dp, color = PrimaryDefault, shape = RoundedCornerShape(14.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(width = 1.dp, color = PrimaryLight, shape = RoundedCornerShape(10.dp)),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier.size(32.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    AsyncImage(
                        model = "",
                        contentDescription = "사진",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                    )

                    // TODO: 방장인 경우에만 퍄냐ㅠㅣㄷ
                    Image(
                        painter = painterResource(id = R.drawable.icon_pencil),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "멤버 이름",
                    style = Body2Bold
                )

                Spacer(modifier = Modifier.width(5.dp))

                // TODO: 준비 상태인 경우에만 visible
                Box(
                    modifier = Modifier
                        .background(PrimaryDefault, shape = RoundedCornerShape(8.dp)),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 2.dp, horizontal = 8.dp),
                        text = "ready",
                        fontFamily = PyeojinGothicFallback,
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp,
                        color = White
                    )
                }

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RoomMemberItemPreview() {
    RoomMemberItem()
}