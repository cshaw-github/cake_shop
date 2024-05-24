package com.vervaintech.data.repository

import com.vervaintech.data.datasource.ServiceApi
import com.vervaintech.data.entities.Response
import com.vervaintech.utils.testdata.MockApiResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class CakeRepositoryImplTest {
	@MockK
	private lateinit var serviceApi: ServiceApi

	@Before
	fun setUp() {
		MockKAnnotations.init(this, relaxUnitFun = true)
	}

	@Test
	fun getCakesSuccessfullyTest(): Unit = runTest {
		val response = Response.success(MockApiResponse.CAKES)
		coEvery { serviceApi.getCakes() } returns flowOf(response)

		CakeRepositoryImpl(serviceApi).getCakes().collect { status ->
			assertTrue { status.successful }
			assertTrue { status.data.isNotEmpty() }
			assertTrue { response.data.size == 20 }
			assertTrue { status.data.size == 5 }
		}
	}

	@Test
	fun getCakesReturnErrorTest(): Unit = runTest {
		val response = Response.error("Bad Request")
		coEvery { serviceApi.getCakes() } returns flowOf(response)

		CakeRepositoryImpl(serviceApi).getCakes().collect { status ->
			assertTrue { !status.successful }
			assertTrue { status.data.isEmpty() }
			assertTrue { status.error == "Bad Request" }
		}
	}
}
