package com.pickpick.pickpick.core.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpick.pickpick.core.ui.theme.G200
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.PrimaryLighter
import com.pickpick.pickpick.core.ui.theme.White
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body1Regular

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
        Text(text = text, style = Body1Regular)
    }
}

@Preview(showBackground = true)
@Composable
fun MainButtonPreview() {
    MainButton(
        text = "main button",
        onClick = {}
    )
}
