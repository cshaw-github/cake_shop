package com.vervaintech.domain.usecase

import com.vervaintech.domain.entities.CakeEntity
import com.vervaintech.domain.repositories.CakeRepository
import kotlinx.coroutines.flow.Flow

internal class GetCakesUseCaseImpl(
	private val repository: CakeRepository,
): GetCakesUseCase{
	override suspend fun invoke(): Flow<List<CakeEntity>> {
		TODO("Not yet implemented")
	}
}
