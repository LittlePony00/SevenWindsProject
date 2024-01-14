package com.example.sevenwindsproject.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sevenwindsproject.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val SevenWindsFamily = FontFamily(
    Font(R.font.sf_ui_display_normal, FontWeight.Normal)
)

private val SfUiDisplayNormal = TextStyle(
    fontFamily = SevenWindsFamily,
    fontWeight = FontWeight.Normal,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

val SfUiDisplayNormal15 = SfUiDisplayNormal.copy(fontSize = 15.sp)
val SfUiDisplayNormal18 = SfUiDisplayNormal.copy(fontSize = 18.sp)