package com.example.sevenwindsproject.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val fieldSpacer: Dp = 4.dp,
    val nearestRestaurantTextSpacer: Dp = 6.dp,
    val standardSpacer: Dp = 12.dp,
    val standardCardElevation: Dp = 12.dp,
    val standardShape: Dp = 24.dp,
    val nearestRestaurantShape: Dp = 5.dp,
    val horizontalPadding: Dp = 16.dp,
    val paddingSmall: Dp = 8.dp,
    val normalSize: Dp = 24.dp
)

val LocalDimensions = compositionLocalOf { Dimensions() }
