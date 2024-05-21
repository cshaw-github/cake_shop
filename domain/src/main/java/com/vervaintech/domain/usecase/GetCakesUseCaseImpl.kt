package com.vervaintech.domain.usecase

import com.vervaintech.domain.entities.CakeEntity

internal class GetCakesUseCaseImpl: GetCakesUseCase{
	override suspend fun invoke(): List<CakeEntity> {
		TODO("Not yet implemented, also return value should be Flow<List<CakeEntity>>")
	}
}
