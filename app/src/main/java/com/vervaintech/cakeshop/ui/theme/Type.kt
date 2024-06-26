package com.vervaintech.cakeshop.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vervaintech.cakeshop.R

@Composable
private fun montserratFontFamily() = FontFamily(
	Font(R.font.montserrat_light, FontWeight.Light),
	Font(R.font.montserrat_regular, FontWeight.Normal),
	Font(R.font.montserrat_medium, FontWeight.Medium),
	Font(R.font.montserrat_semibold, FontWeight.SemiBold),
)

@Composable
private fun karlaFontFamily() = FontFamily(
	Font(R.font.karla_regular, FontWeight.Normal),
	Font(R.font.karla_bold, FontWeight.Bold),
)

@Composable
fun shopTypography() = Typography(
	displayLarge = TextStyle(
		fontFamily = montserratFontFamily(),
		fontWeight = FontWeight.Light,
		fontSize = 57.sp,
		lineHeight = 64.sp,
		letterSpacing = 0.sp,
	),
	displayMedium = TextStyle(
		fontFamily = montserratFontFamily(),
		fontWeight = FontWeight.Light,
		fontSize = 45.sp,
		lineHeight = 52.sp,
		letterSpacing = 0.sp,
	),
	displaySmall = TextStyle(
		fontFamily = montserratFontFamily(),
		fontWeight = FontWeight.Normal,
		fontSize = 36.sp,
		lineHeight = 44.sp,
		letterSpacing = 0.sp,
	),
	headlineLarge = TextStyle(
		fontFamily = montserratFontFamily(),
		fontWeight = FontWeight.SemiBold,
		fontSize = 32.sp,
		lineHeight = 40.sp,
		letterSpacing = 0.sp,
	),
	headlineMedium = TextStyle(
		fontFamily = montserratFontFamily(),
		fontWeight = FontWeight.SemiBold,
		fontSize = 28.sp,
		lineHeight = 36.sp,
		letterSpacing = 0.sp,
	),
	headlineSmall = TextStyle(
		fontFamily = montserratFontFamily(),
		fontWeight = FontWeight.SemiBold,
		fontSize = 24.sp,
		lineHeight = 32.sp,
		letterSpacing = 0.sp,
	),
	titleLarge = TextStyle(
		fontFamily = montserratFontFamily(),
		fontWeight = FontWeight.SemiBold,
		fontSize = 22.sp,
		lineHeight = 28.sp,
		letterSpacing = 0.sp,
	),
	titleMedium = TextStyle(
		fontFamily = montserratFontFamily(),
		fontWeight = FontWeight.SemiBold,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.15.sp,
	),
	titleSmall = TextStyle(
		fontFamily = karlaFontFamily(),
		fontWeight = FontWeight.Bold,
		fontSize = 14.sp,
		lineHeight = 20.sp,
		letterSpacing = 0.1.sp,
	),
	bodyLarge = TextStyle(
		fontFamily = karlaFontFamily(),
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.15.sp,
	),
	bodyMedium = TextStyle(
		fontFamily = montserratFontFamily(),
		fontWeight = FontWeight.Medium,
		fontSize = 14.sp,
		lineHeight = 20.sp,
		letterSpacing = 0.25.sp,
	),
	bodySmall = TextStyle(
		fontFamily = karlaFontFamily(),
		fontWeight = FontWeight.Bold,
		fontSize = 12.sp,
		lineHeight = 16.sp,
		letterSpacing = 0.4.sp,
	),
	labelLarge = TextStyle(
		fontFamily = montserratFontFamily(),
		fontWeight = FontWeight.SemiBold,
		fontSize = 14.sp,
		lineHeight = 20.sp,
		letterSpacing = 0.1.sp,
	),
	labelMedium = TextStyle(
		fontFamily = montserratFontFamily(),
		fontWeight = FontWeight.SemiBold,
		fontSize = 12.sp,
		lineHeight = 16.sp,
		letterSpacing = 0.5.sp,
	),
	labelSmall = TextStyle(
		fontFamily = montserratFontFamily(),
		fontWeight = FontWeight.SemiBold,
		fontSize = 11.sp,
		lineHeight = 16.sp,
		letterSpacing = 0.5.sp,
	),
)
