package com.vervaintech.data.entities

import kotlinx.serialization.json.Json


data class Response(
	val data: List<CakeEntity> = emptyList(),
	val successful: Boolean = data.isNotEmpty(),
	val error: String? = null,
) {
	companion object {
		private val json = Json { ignoreUnknownKeys = true }

		fun success(
			responseBody: String
		): Response =
			Response(data = json.decodeFromString<List<CakeEntity>>(responseBody))

		fun error(
			responseBody: String
		) = Response(error = responseBody)
	}
}
