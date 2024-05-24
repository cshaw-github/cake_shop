package com.vervaintech.cakeshop.ui.screens.home

import com.vervaintech.cakeshop.ui.model.UiStatus
import com.vervaintech.domain.entities.CakeEntity
import com.vervaintech.domain.entities.Status
import com.vervaintech.domain.usecase.GetCakesUseCase
import com.vervaintech.utils.Uitls.NO_NETWORK
import com.vervaintech.utils.Uitls.UNKNOWN_ERROR
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class HomeScreenViewModelTest {
	@MockK
	private lateinit var getCakesUseCase: GetCakesUseCase

	@Before
	fun setUp() {
		MockKAnnotations.init(this, relaxUnitFun = true)
	}

	@Test
	fun testGetAllCakesSuccessfully() = runTest {
		coEvery { getCakesUseCase.invoke() } returns flowOf(
			Status(
				data = listOf(CakeEntity(title = "title", desc = "description", image = ""))
			)
		)

		val viewModel = HomeScreenViewModel(getCakesUseCase)

		viewModel.getCakes()
		assertTrue { viewModel.cakesState.value.size == 1 }
		assertTrue { viewModel.uiState.value == UiStatus.Success }
	}

	@Test
	fun testGetAllCakesNoInternetError() = runTest {
		coEvery { getCakesUseCase.invoke() } returns flowOf(
			Status(error = NO_NETWORK)
		)

		val viewModel = HomeScreenViewModel(getCakesUseCase)

		viewModel.getCakes()
		assertTrue { viewModel.uiState.value is UiStatus.Error && (viewModel.uiState.value as UiStatus.Error).message == NO_NETWORK }
	}

	@Test
	fun testGetAllCakesUnknownError() = runTest {
		coEvery { getCakesUseCase.invoke() } returns flowOf(
			Status(error = null)
		)

		val viewModel = HomeScreenViewModel(getCakesUseCase)

		viewModel.getCakes()
		assertTrue { viewModel.uiState.value is UiStatus.Error && (viewModel.uiState.value as UiStatus.Error).message == UNKNOWN_ERROR }
	}
}
