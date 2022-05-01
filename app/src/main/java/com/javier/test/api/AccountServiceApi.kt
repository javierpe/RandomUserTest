package com.javier.test.api

import com.javier.test.states.ResponseState
import kotlinx.coroutines.flow.Flow

interface AccountServiceApi {

    suspend fun load(): Flow<ResponseState>
}