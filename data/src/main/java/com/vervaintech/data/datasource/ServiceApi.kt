package com.vervaintech.data.datasource

import com.vervaintech.data.entities.Response
import kotlinx.coroutines.flow.Flow

interface ServiceApi {
	suspend fun getCakes(): Flow<Response>
}
