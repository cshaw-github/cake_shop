package com.vervaintech.datasource.api

import com.vervaintech.utils.testdata.MockApiResponse
import io.ktor.client.HttpClient
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Test
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds

internal class ServiceApiImplTest {
	@Test
	fun getCakesSuccessfullyTest() = runTest {
		val engine = getMockEngine(
			response =  MockApiResponse.CAKES,
			returnStatusCode = HttpStatusCode.OK
		)
		val httpClient = HttpClient(engine = engine)
		val service = ServiceApiImpl(httpClient)

		withTimeoutOrNull(5.seconds) {
			service.getCakes().collect {
				assertTrue { it.successful }
				assertTrue { it.data.isNotEmpty() }
				assertTrue { it.data.size == 20 }
				assertTrue { it.data.filter { cake -> cake.title == "Banana cake" }.size == 4 }
				assertTrue { it.data.filter { cake -> cake.title == "Birthday cake" }.size == 4 }
				assertTrue { it.data.filter { cake -> cake.title == "Carrot cake" }.size == 4 }
				assertTrue { it.data.filter { cake -> cake.title == "Lemon cheesecake" }.size == 4 }
				assertTrue { it.data.filter { cake -> cake.title == "victoria sponge" }.size == 4 }
				assertTrue { it.error == null }
			}
		}
	}

	@Test
	fun getCakesReturnsErrorTest() = runTest {
		val engine = getMockEngine(
			response = "BadGateway",
			returnStatusCode = HttpStatusCode.BadGateway
		)
		val httpClient = HttpClient(engine = engine)
		val service = ServiceApiImpl(httpClient)

		service.getCakes().collect {
			assertTrue { !it.successful }
			assertTrue { it.error != null }
			assertTrue { it.data.isEmpty() }
		}
	}
}
