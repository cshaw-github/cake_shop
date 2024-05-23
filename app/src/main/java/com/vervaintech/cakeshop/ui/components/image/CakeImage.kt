package com.vervaintech.cakeshop.ui.components.image

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vervaintech.cakeshop.ui.dimens.Dimens


@Composable
fun CakeImage(
	modifier: Modifier = Modifier,
	imageUrl: String,
	contentDescription: String? = null,
	imageContentScale: ContentScale = ContentScale.Crop,
	imageHeight: Dp = Dimens.xxxLarge,
	roundedCornerShape: CornerBasedShape = MaterialTheme.shapes.large
) {
	AsyncImage(
		modifier = modifier
			.height(imageHeight)
			.clip(roundedCornerShape),
		model = ImageRequest.Builder(LocalContext.current)
			.data(imageUrl)
			.crossfade(true)
			.build(),
		contentScale = imageContentScale,
		contentDescription = contentDescription,
	)
}
