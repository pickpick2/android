package com.pickpick.pickpick.core.ui.theme.font

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicFallback

object PyeojinGothicTypography {
    val Heading1 = TextStyle(
        fontFamily = PyeojinGothicFallback,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    )
    val Heading2 = TextStyle(
        fontFamily = PyeojinGothicFallback,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    )
    val Heading3 = TextStyle(
        fontFamily = PyeojinGothicFallback,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    )

    val Body1Bold = TextStyle(
        fontFamily = PyeojinGothicFallback,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 16.sp * 1.2
    )
    val Body1SemiBold = TextStyle(
        fontFamily = PyeojinGothicFallback,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 16.sp * 1.2
    )
    val Body1Regular = TextStyle(
        fontFamily = PyeojinGothicFallback,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 16.sp * 1.2
    )

    val Body2Bold = TextStyle(
        fontFamily = PyeojinGothicFallback,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 14.sp * 1.2
    )
    val Body2Regular = TextStyle(
        fontFamily = PyeojinGothicFallback,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 14.sp * 1.2
    )

    val DetailBold = TextStyle(
        fontFamily = PyeojinGothicFallback,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 12.sp * 1.5
    )
    val DetailRegular = TextStyle(
        fontFamily = PyeojinGothicFallback,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 12.sp * 1.5
    )
    val DetailLight = TextStyle(
        fontFamily = PyeojinGothicFallback,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        lineHeight = 12.sp * 1.5
    )

}
