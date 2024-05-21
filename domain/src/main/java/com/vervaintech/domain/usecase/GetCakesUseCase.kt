package com.vervaintech.domain.usecase

import com.vervaintech.domain.entities.CakeEntity

interface GetCakesUseCase {
	suspend operator fun invoke(): List<CakeEntity>
}
