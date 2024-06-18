package com.vervaintech.domain.usecase

import com.vervaintech.domain.entities.Status
import com.vervaintech.domain.repositories.CakeRepository
import kotlinx.coroutines.flow.Flow

internal class GetCakesUseCaseImpl(
	private val repository: CakeRepository,
) : GetCakesUseCase {
	override suspend fun invoke(): Flow<Status> = repository.getCakes()
}
