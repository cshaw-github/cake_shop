package com.vervaintech.domain.usecase

import com.vervaintech.domain.entities.CakeEntity
import com.vervaintech.domain.entities.Status
import com.vervaintech.domain.repositories.CakeRepository
import com.vervaintech.domain.usecase.MockApiResponse.CAKES
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class GetCakesUseCaseImplTest {

	@MockK
	private lateinit var repository: CakeRepository

	@Before
	fun setUp() {
		MockKAnnotations.init(this, relaxUnitFun = true)
	}

	@Test
	fun getCakesUseCaseSuccessfullyTest(): Unit = runTest {
		val response = getStatus(success = true)
		coEvery { repository.getCakes() } returns flowOf(response)

		val getCakesUseCase = GetCakesUseCaseImpl(repository)

		getCakesUseCase.invoke().collect { status ->
			assertTrue { status.successful }
			assertTrue { response.data == status.data }
			assertTrue { status.data.size == 5 }
		}
	}

	@Test
	fun getCakesUseCaseReturnsErrorTest(): Unit = runTest {
		coEvery { repository.getCakes() } returns flowOf(getStatus(success = false))

		val getCakesUseCase = GetCakesUseCaseImpl(repository)

		getCakesUseCase.invoke().collect { status ->
			assertTrue { !status.successful }
			assertTrue { status.data.isEmpty() }
			assertTrue { status.error != null }
		}
	}

	private fun getStatus(success: Boolean): Status {
		val data = Json.decodeFromString<List<CakeEntity>>(CAKES)
			.distinct().sortedBy { it.title }

		return Status(
			successful = success,
			data = if (success) data else emptyList(),
			error = if (success) null else "Bad Request"
		)
	}
}
