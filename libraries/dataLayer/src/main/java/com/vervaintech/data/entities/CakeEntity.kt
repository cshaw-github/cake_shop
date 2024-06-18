package com.vervaintech.data.entities

import kotlinx.serialization.Serializable


@Serializable
data class CakeEntity(
	val title: String,
	val desc: String,
	val image: String,
)
