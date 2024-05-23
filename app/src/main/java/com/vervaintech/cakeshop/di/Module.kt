package com.vervaintech.cakeshop.di

import com.vervaintech.cakeshop.ui.screens.home.HomeScreenViewModel
import com.vervaintech.data.di.dataLayerModule
import com.vervaintech.datasource.di.dataSourceModule
import com.vervaintech.domain.di.domainLayerModule
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val applicationLayerModule = module {
	factoryOf(::HomeScreenViewModel)
}

fun initKoin() {
	startKoin {
		modules(
			applicationLayerModule,
			domainLayerModule,
			dataLayerModule,
			dataSourceModule(enableNetworkLogs = false),
		)
	}
}
