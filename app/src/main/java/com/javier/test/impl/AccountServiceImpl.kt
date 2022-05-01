package com.javier.test.impl

import com.google.gson.Gson
import com.javier.test.api.AccountServiceApi
import com.javier.test.models.AccountResults
import com.javier.test.states.ResponseState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext

class AccountServiceImpl(
    private val client: HttpClient,
    private val gson: Gson
): AccountServiceApi {

    override suspend fun load(): Flow<ResponseState> = channelFlow {
        send(ResponseState.OnStart)

        withContext(Dispatchers.IO) {
            try {

                val response = client.get(URL)
                val data = gson.fromJson(response.bodyAsText(), AccountResults::class.java)
                send(ResponseState.OnSuccess(data))
            } catch (e: Exception) {
                send(ResponseState.OnError(e))
            }
        }
    }

    companion object {
        const val URL = "https://randomuser.me/api/?results=50"
    }
}