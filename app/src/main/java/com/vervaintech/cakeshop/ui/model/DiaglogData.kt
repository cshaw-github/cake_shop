package com.vervaintech.cakeshop.ui.model

data class DialogData(
	val showDialog: Boolean = false,
	val cake: CakeEntity = CakeEntity(
		title = "title",
		desc = "desc",
		image = "image"
	)
)
