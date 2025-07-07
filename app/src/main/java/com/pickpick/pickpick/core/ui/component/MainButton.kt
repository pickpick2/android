package com.pickpick.pickpick.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.theme.G200
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.PrimaryLighter
import com.pickpick.pickpick.core.ui.theme.White
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body1Regular
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body2Bold

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    text: String ,
    onClick: () -> Unit ,
    shape: Shape = RoundedCornerShape(10.dp),
    enabled: Boolean = true,
) {
    TextButton(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = ButtonColors(
            containerColor = PrimaryDefault,
            contentColor = White,
            disabledContainerColor = PrimaryLighter,
            disabledContentColor = G200
        )
    ) {
        Text(text = text, style = Body2Bold)
    }
}

@Composable
fun IconBorderButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = PrimaryDefault,
                shape = RoundedCornerShape(10.dp)
            )
            .height(40.dp),
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = PrimaryLighter,
            contentColor = PrimaryDefault
        ),
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_invite),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = text,
            style = Body2Bold.copy(color = PrimaryDefault)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainButtonPreview() {
    MainButton(
        text = "main button",
        onClick = {}
    )

    IconBorderButton(
        text = stringResource(R.string.invite),
        onClick = {}
    )
}
