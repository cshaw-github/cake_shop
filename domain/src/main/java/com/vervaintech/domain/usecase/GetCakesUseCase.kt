package com.vervaintech.domain.usecase

import com.vervaintech.domain.entities.CakeEntity
import kotlinx.coroutines.flow.Flow

interface GetCakesUseCase {
	suspend operator fun invoke(): Flow<List<CakeEntity>>
}
