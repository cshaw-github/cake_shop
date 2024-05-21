package com.vervaintech.cakeshop.di

import com.vervaintech.data.di.dataLayerModule
import com.vervaintech.datasource.di.dataSourceModule
import com.vervaintech.domain.di.domainLayerModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

val applicationLayerModule = module {

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
