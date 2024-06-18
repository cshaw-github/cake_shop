package com.vervaintech.cakeshop.ui.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.vervaintech.cakeshop.ui.mapper.toUi
import com.vervaintech.cakeshop.ui.model.UiStatus
import com.vervaintech.domain.entities.Status
import com.vervaintech.domain.usecase.GetCakesUseCase
import com.vervaintech.utils.Uitls.UNKNOWN_ERROR
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import com.vervaintech.cakeshop.ui.model.CakeEntity

class HomeScreenViewModel(
	private val getCakesUseCase: GetCakesUseCase,
) : ScreenModel {

	init {
		screenModelScope.launch(IO) {
			getCakes()
		}
	}

	val cakesState: StateFlow<List<CakeEntity>>
		field = MutableStateFlow<List<CakeEntity>>(emptyList<CakeEntity>())

	val uiState: StateFlow<UiStatus>
		field = MutableStateFlow<UiStatus>(UiStatus.None)

	suspend fun getCakes() {
		getCakesUseCase()
			.onStart { uiState.value = UiStatus.Loading }
			.onEach(::validateLogin)
			.catch {}
			.collect()
	}

	private fun validateLogin(
		status: Status,
	) {
		when (status.successful) {
			true -> {
				cakesState.value = status.data.map { it.toUi() }
				uiState.value = UiStatus.Success
			}

			false -> {
				uiState.value = UiStatus.Error(status.error ?: UNKNOWN_ERROR)
			}
		}
	}
}
