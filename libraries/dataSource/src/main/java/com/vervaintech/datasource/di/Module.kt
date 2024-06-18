package com.vervaintech.datasource.di

import com.vervaintech.data.datasource.ServiceApi
import com.vervaintech.datasource.api.ServiceApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun dataSourceModule(enableNetworkLogs: Boolean) = module {
	single { getHttpClient(enableNetworkLogs) }
	single<ServiceApi> { ServiceApiImpl(get()) }
}

private fun getHttpClient(enableNetworkLogs: Boolean): HttpClient {
	val json = Json {
		prettyPrint = true
		isLenient = true
		ignoreUnknownKeys = true
	}

	return HttpClient {
		install(ContentNegotiation) {
			json(json)
		}
		if (enableNetworkLogs) {
			install(Logging) {
				logger = Logger.SIMPLE
				level = LogLevel.ALL
			}
		}
	}
}
