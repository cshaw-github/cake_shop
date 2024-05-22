package com.vervaintech.datasource.api

import com.vervaintech.data.datasource.ServiceApi
import com.vervaintech.data.entities.Response
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.flow

internal class ServiceApiImpl(
	private val httpClient: HttpClient,
) : ServiceApi {
	override suspend fun getCakes(): Flow<Response> = flow {
		val response = httpClient.get(URL) {
			contentType(ContentType.Application.Json)
		}
		emit(processResponse(response))
	}

	private suspend fun processResponse(
		response: HttpResponse,
	): Response = if (response.status.isSuccess()) {
		Response.success(response.bodyAsText())
	} else {
		Response.error(response.bodyAsText())
	}

	private companion object {
		private const val URL =
			"https://gist.githubusercontent.com/t-reed/739df99e9d96700f17604a3971e701fa/raw/1d4dd9c5a0ec758ff5ae92b7b13fe4d57d34e1dc/waracle_cake-android-client"
	}
}
