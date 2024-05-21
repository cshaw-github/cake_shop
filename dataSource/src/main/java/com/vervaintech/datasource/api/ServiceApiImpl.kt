package com.vervaintech.datasource.api

import com.vervaintech.data.datasource.ServiceApi
import io.ktor.client.HttpClient

internal class ServiceApiImpl(
	private val httpClient: HttpClient,
) : ServiceApi
