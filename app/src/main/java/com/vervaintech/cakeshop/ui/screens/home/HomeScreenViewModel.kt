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
import com.vervaintech.cakeshop.ui.model.CakeEntity as UiCakeEntity

class HomeScreenViewModel(
	private val getCakesUseCase: GetCakesUseCase,
) : ScreenModel {

	init {
		screenModelScope.launch(IO) {
			getCakes()
		}
	}

	private val _cakesState = MutableStateFlow(emptyList<UiCakeEntity>())
	val cakesState: StateFlow<List<UiCakeEntity>>
		get() = _cakesState

	private val _uiState = MutableStateFlow<UiStatus>(UiStatus.None)
	val uiState: StateFlow<UiStatus>
		get() = _uiState

	suspend fun getCakes() {
		getCakesUseCase()
			.onStart { _uiState.value = UiStatus.Loading }
			.onEach(::validateLogin)
			.catch {}
			.collect()
	}

	private fun validateLogin(
		status: Status,
	) {
		when (status.successful) {
			true -> {
				_cakesState.value = status.data.map { it.toUi() }
				_uiState.value = UiStatus.Success
			}

			false -> {
				_uiState.value = UiStatus.Error(status.error ?: UNKNOWN_ERROR)
			}
		}
	}
}
