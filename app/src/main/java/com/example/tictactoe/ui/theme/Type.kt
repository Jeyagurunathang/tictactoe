package com.example.tictactoe.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.tictactoe.R

val nunitoBold = FontFamily(
    Font(R.font.nunitosans_bold)
)

val nunitoSemiBold = FontFamily(
    Font(R.font.nunitosans_semibold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = nunitoSemiBold,
        fontSize = 18.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = nunitoBold,
        fontSize = 24.sp
    ),
    labelLarge = TextStyle(
        fontFamily = nunitoBold,
        fontSize = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = nunitoBold,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)