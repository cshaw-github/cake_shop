package com.vervaintech.data.di

import com.vervaintech.data.repository.CakeRepositoryImpl
import com.vervaintech.domain.repositories.CakeRepository
import org.koin.dsl.module


val dataLayerModule = module {
	single<CakeRepository> { CakeRepositoryImpl(get()) }
}
