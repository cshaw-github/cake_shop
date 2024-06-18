package com.vervaintech.data.repository

import com.vervaintech.data.datasource.ServiceApi
import com.vervaintech.data.entities.Response
import com.vervaintech.data.mapper.toDomain
import com.vervaintech.domain.entities.Status
import com.vervaintech.domain.repositories.CakeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalCoroutinesApi::class)
internal class CakeRepositoryImpl(
	private val serviceApi: ServiceApi,
) : CakeRepository {

	override suspend fun getCakes(): Flow<Status> {
		return serviceApi
			.getCakes()
			.flatMapLatest(::validateAndRemoveDuplicates)
	}

	private fun validateAndRemoveDuplicates(
		response: Response
	): Flow<Status> {
		val status = if (response.successful) {
			val data = response.data.distinct().sortedBy { it.title }.map { it.toDomain() }
			Status(data = data)
		} else {
			Status(error = response.error ?: "UNKNOWN_ERROR")
		}

		return flowOf(status)
	}
}
