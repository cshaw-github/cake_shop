package com.vervaintech.datasource.api

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

fun getMockEngine(
	response: String,
	returnStatusCode: HttpStatusCode,
) = MockEngine { _ ->
	respond(
		content = response,
		status = returnStatusCode,
		headers = headersOf(HttpHeaders.ContentType, "application/json")
	)
}
