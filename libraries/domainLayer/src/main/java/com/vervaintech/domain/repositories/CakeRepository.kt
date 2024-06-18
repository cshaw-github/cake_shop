package com.vervaintech.domain.repositories

import com.vervaintech.domain.entities.Status
import kotlinx.coroutines.flow.Flow

interface CakeRepository {
	suspend fun getCakes(): Flow<Status>
}
