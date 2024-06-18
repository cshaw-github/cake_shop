package com.vervaintech.domain.usecase

import com.vervaintech.domain.entities.Status
import kotlinx.coroutines.flow.Flow

interface GetCakesUseCase {
	suspend operator fun invoke(): Flow<Status>
}
