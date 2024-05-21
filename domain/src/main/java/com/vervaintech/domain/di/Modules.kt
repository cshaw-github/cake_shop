package com.vervaintech.domain.di

import com.vervaintech.domain.usecase.GetCakesUseCase
import com.vervaintech.domain.usecase.GetCakesUseCaseImpl
import org.koin.dsl.module

val domainLayerModule = module {
	single<GetCakesUseCase> { GetCakesUseCaseImpl(get()) }
}


