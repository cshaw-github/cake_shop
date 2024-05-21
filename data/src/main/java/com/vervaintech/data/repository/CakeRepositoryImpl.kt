package com.vervaintech.data.repository

import com.vervaintech.data.datasource.ServiceApi
import com.vervaintech.domain.repositories.CakeRepository

internal class CakeRepositoryImpl(
	private val serviceApi: ServiceApi,
): CakeRepository
