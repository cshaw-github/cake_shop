package com.vervaintech.cakeshop.ui.components.imagecaption

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vervaintech.cakeshop.ui.components.image.CakeImage
import com.vervaintech.cakeshop.ui.dimens.Dimens
import com.vervaintech.cakeshop.ui.model.CakeEntity
import com.vervaintech.cakeshop.ui.theme.CakeShopTheme

@Composable
fun VerticalImageCaptionCard(
	modifier: Modifier = Modifier,
	cake: CakeEntity,
	titleStyle: TextStyle = MaterialTheme.typography.titleLarge,
	contentDescription: String? = null,
	imageContentScale: ContentScale = ContentScale.Crop,
	imageHeight: Dp = Dimens.xxxLarge,
	roundedCornerShape: CornerBasedShape = MaterialTheme.shapes.medium,
	onItemClick: (CakeEntity) -> Unit
) {
	ElevatedCard(
		modifier = modifier.wrapContentWidth(),
		elevation = CardDefaults.cardElevation(
			defaultElevation = 6.dp
		),
	) {
		VerticalImageCaption(
			titleStyle = titleStyle,
			cake = cake,
			contentDescription = contentDescription,
			imageContentScale = imageContentScale,
			imageHeight = imageHeight,
			roundedCornerShape = roundedCornerShape,
			onItemClick = onItemClick

		)
	}
}

@Composable
fun VerticalImageCaption(
	modifier: Modifier = Modifier,
	cake: CakeEntity,
	titleStyle: TextStyle = MaterialTheme.typography.titleLarge,
	contentDescription: String? = null,
	imageContentScale: ContentScale = ContentScale.Fit,
	imageHeight: Dp = Dimens.xLarge,
	roundedCornerShape: CornerBasedShape = MaterialTheme.shapes.medium,
	onItemClick: (CakeEntity) -> Unit
) {
	Column(
		modifier = modifier
            .wrapContentWidth()
            .padding(16.dp)
			.clickable { onItemClick(cake) },
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		CakeImage(
			imageUrl = cake.image,
			contentDescription = contentDescription,
			imageContentScale = imageContentScale,
			imageHeight = imageHeight,
			roundedCornerShape = roundedCornerShape,
		)
		Spacer(modifier = Modifier.height(16.dp))
		Text(
			text = cake.title,
			style = titleStyle,
			color = MaterialTheme.colorScheme.onSurface
		)
		Spacer(modifier = Modifier.height(16.dp))
	}
}

@Preview
@PreviewLightDark
@Composable
fun VerticalImageCaptionCardPreview() {
	Surface {
		CakeShopTheme {
			Surface {
				VerticalImageCaptionCard(
					cake = CakeEntity("title", "desc", "image")
				) {}
			}
		}
	}
}
