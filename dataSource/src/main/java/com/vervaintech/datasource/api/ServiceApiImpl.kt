package com.vervaintech.datasource.api

import com.vervaintech.data.datasource.ServiceApi
import com.vervaintech.data.entities.Response
import com.vervaintech.utils.Uitls.NO_NETWORK
import com.vervaintech.utils.Uitls.UNKNOWN_ERROR
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeoutOrNull
import java.net.ConnectException
import kotlin.time.Duration.Companion.seconds

internal class ServiceApiImpl(
	private val httpClient: HttpClient,
) : ServiceApi {
	override suspend fun getCakes(): Flow<Response> = flow {
		val response: HttpResponse?
		try {
			response = withTimeoutOrNull(5.seconds) {
				httpClient.get(URL) {
					contentType(ContentType.Application.Json)
				}
			}
			emit(processResponse(response))
		} catch (e: Exception) {
			emit(processResponse(exception = e))
		}
	}

	private suspend fun processResponse(
		response: HttpResponse? = null,
		exception: Exception? = null,
	): Response = if (response?.status?.isSuccess() == true) {
		Response.success(response.bodyAsText())
	} else if (response?.status?.isSuccess() == false) {
		Response.error(response.bodyAsText())
	} else {
		val error = when (exception) {
			is ConnectException -> NO_NETWORK
			else -> UNKNOWN_ERROR
		}
		Response.error(error)
	}

	private companion object {
		private const val URL =
			"https://gist.githubusercontent.com/t-reed/739df99e9d96700f17604a3971e701fa/raw/1d4dd9c5a0ec758ff5ae92b7b13fe4d57d34e1dc/waracle_cake-android-client"
	}
}
