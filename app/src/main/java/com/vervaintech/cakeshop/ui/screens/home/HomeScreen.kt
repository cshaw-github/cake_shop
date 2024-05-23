package com.vervaintech.cakeshop.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.vervaintech.cakeshop.R
import com.vervaintech.cakeshop.ui.components.imagecaption.VerticalImageCaptionCard
import com.vervaintech.cakeshop.ui.components.snackbar.CakeSnackbar
import com.vervaintech.cakeshop.ui.dimens.Dimens
import com.vervaintech.cakeshop.ui.model.CakeEntity
import com.vervaintech.cakeshop.ui.model.UiStatus
import com.vervaintech.cakeshop.ui.screens.ValidateUiStatus
import com.vervaintech.cakeshop.ui.theme.CakeShopTheme
import kotlinx.coroutines.delay

data object HomeScreen : Screen {
    private fun readResolve(): Any = HomeScreen

    @Composable
    override fun Content() {
        HomeContent(
            viewModel = getScreenModel<HomeScreenViewModel>(),
            navigator = LocalNavigator.currentOrThrow,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
    viewModel: HomeScreenViewModel,
    navigator: Navigator,
) {
    val snackbar = remember { CakeSnackbar(SnackbarHostState()) }

    val pullRefreshState = rememberPullToRefreshState()
    if (pullRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            viewModel.getCakes()
            delay(1500)
            pullRefreshState.endRefresh()
        }
    }

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
            errorMessage = stringResource(R.string.no_internet),
            actionLabel = stringResource(R.string.try_again),
            onActionPerformed = { viewModel.resetUiState() },
            onSuccess = { navigator.push(HomeScreen) },
        )

        if (uiState != UiStatus.Loading) {
            Home(cakes = cakes, listState, pullRefreshState, contentPadding)
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
                    VerticalImageCaptionCard(
                        imageUrl = cakes[index].image,
                        title = cakes[index].title,
                    )
                }
            }
        }

        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullToRefreshState,
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
                navigator = navigator
            )
        }
    }
}

