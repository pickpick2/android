package com.pickpick.pickpick.presentation.album.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.Border
import com.pickpick.pickpick.core.ui.theme.Danger
import com.pickpick.pickpick.core.ui.theme.White
import com.pickpick.pickpick.core.ui.theme.clickableNoRipple
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body1Bold
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body2Bold
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.DetailRegular

@Composable
fun DeletePhotoDialog(
    onDismiss: () -> Unit,
    onDelete: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickableNoRipple(
                onClick = onDismiss
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 14.dp)
                .background(White, shape = RoundedCornerShape(10.dp))
                .wrapContentSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    IconButton(onClick = onDismiss) {
                        Image(
                            painter = painterResource(R.drawable.icon_cancel),
                            contentDescription = "닫기"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = stringResource(R.string.dialog_photo_delete_title),
                    style = Body1Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(R.string.dialog_photo_delete_sub_title),
                    style = DetailRegular
                )

                Spacer(modifier = Modifier.height(14.dp))

                // 버튼
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(Border)
                            .clip(RoundedCornerShape(10.dp))
                            .clickableNoRipple(
                                onClick = onDismiss
                            )
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = Body2Bold,
                            color = White
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(Danger)
                            .clip(RoundedCornerShape(10.dp))
                            .clickableNoRipple(
                                onClick = onDelete
                            )
                    ) {
                        Text(
                            text = stringResource(R.string.delete),
                            style = Body2Bold,
                            color = White
                        )
                    }
                }
            }
        }
    }
}
