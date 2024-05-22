package com.vervaintech.domain.entities

data class Status(
	val data: List<CakeEntity> = emptyList(),
	val successful: Boolean = data.isNotEmpty(),
	val error: String? = null,
)
