package com.vervaintech.cakeshop.ui.screens.home

import CakeDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.vervaintech.cakeshop.R
import com.vervaintech.cakeshop.ui.components.imagecaption.VerticalImageCaptionCard
import com.vervaintech.cakeshop.ui.components.snackbar.CakeSnackbar
import com.vervaintech.cakeshop.ui.dimens.Dimens
import com.vervaintech.cakeshop.ui.model.CakeEntity
import com.vervaintech.cakeshop.ui.model.DialogData
import com.vervaintech.cakeshop.ui.model.UiStatus
import com.vervaintech.cakeshop.ui.screens.ValidateUiStatus
import com.vervaintech.cakeshop.ui.theme.CakeShopTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data object HomeScreen : Screen {
	private fun readResolve(): Any = HomeScreen

	@Composable
	override fun Content() {

		val coroutineScope = rememberCoroutineScope()
		val viewModel = getScreenModel<HomeScreenViewModel>()
		HomeContent(
			viewModel = viewModel,
			coroutineScope,
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
	viewModel: HomeScreenViewModel,
	coroutineScope: CoroutineScope,
) {
	val pullRefreshState = rememberPullToRefreshState()
	if (pullRefreshState.isRefreshing) {
		LaunchedEffect(true) {
			viewModel.getCakes()
			delay(1500)
			pullRefreshState.endRefresh()
		}
	}

	val snackbar = remember { CakeSnackbar(SnackbarHostState()) }

	Scaffold(
		snackbarHost = {
			SnackbarHost(hostState = snackbar.snackbarHostState)
		},
	) { contentPadding ->

		val listState = rememberLazyListState()
		val uiState by viewModel.uiState.collectAsState()
		val cakes by viewModel.cakesState.collectAsState()

		ValidateUiStatus(
			uiStatus = uiState,
			snackbar = snackbar,
			onActionRetry = {
				coroutineScope.launch(IO) {
					viewModel.getCakes()
				}
			},
		)

		if (uiState == UiStatus.Success) {
			Home(cakes = cakes, listState, pullRefreshState, contentPadding)
		} else if (uiState is UiStatus.Error) {
			NoDataAvailableScreen()
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Home(
	cakes: List<CakeEntity>,
	lazyListState: LazyListState,
	pullToRefreshState: PullToRefreshState,
	contentPadding: PaddingValues
) {

	val dialog = remember { mutableStateOf(DialogData()) }

	Box(
        Modifier
            .fillMaxSize()
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
	) {
		LazyColumn(
			contentPadding = contentPadding,
			modifier = Modifier.padding(
				start = Dimens.medium,
				top = Dimens.xLarge,
				bottom = Dimens.xLarge,
				end = Dimens.medium
			),
			state = lazyListState,
			verticalArrangement = Arrangement.spacedBy(Dimens.medium),
		) {
			if (!pullToRefreshState.isRefreshing) {
				items(cakes.size) { index ->
					VerticalImageCaptionCard(cake = cakes[index]) { cake ->
						dialog.value = DialogData(showDialog = true, cake = cake)
					}
				}
			}
		}

		if (dialog.value.showDialog) {
			CakeDialog(
				title = dialog.value.cake.title,
				description = dialog.value.cake.desc,
				onDismissRequest = { dialog.value = DialogData(showDialog = false) }
			)
		}

		PullToRefreshContainer(
			modifier = Modifier.align(Alignment.TopCenter),
			state = pullToRefreshState,
		)
	}
}

@Composable
private fun NoDataAvailableScreen() {
	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = stringResource(R.string.no_data_available),
			style = MaterialTheme.typography.titleLarge
		)
	}
}


@Preview(showBackground = true)
@PreviewLightDark
@Composable
fun HomeScreenPreview() {
	val navigator = LocalNavigator.currentOrThrow
	CakeShopTheme {
		Surface {
			HomeContent(
				viewModel = navigator.getNavigatorScreenModel<HomeScreenViewModel>(),
				coroutineScope = rememberCoroutineScope()
			)
		}
	}
}

