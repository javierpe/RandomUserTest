package com.javier.test

import app.cash.turbine.test
import com.google.gson.Gson
import com.javier.test.impl.AccountServiceImpl
import com.javier.test.models.AccountModel
import com.javier.test.models.AccountName
import com.javier.test.models.AccountResults
import com.javier.test.states.ResponseState
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class AccountServiceTest {

    @Mock
    lateinit var client: HttpClient

    @Mock
    lateinit var response: HttpResponse

    @Mock
    lateinit var gson: Gson

    private lateinit var accountServiceImpl: AccountServiceImpl


    private val results by lazy {
        AccountResults(listOf(AccountModel(name = AccountName("Javier"))))
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        accountServiceImpl = AccountServiceImpl(client, gson)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `OnStart should be emitted first time`() = runTest {
        accountServiceImpl.load().test {
            assert(awaitItem() is ResponseState.OnStart)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `OnError should be emitted when response has errors`() = runTest {

        whenever(client.get(AccountServiceImpl.URL)).thenReturn(response)
        whenever(gson.fromJson(response.bodyAsText(), AccountResults::class.java)).thenReturn(null)

        accountServiceImpl.load().test {
            assert(awaitItem() is ResponseState.OnError)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `OnSuccess should be emitted when response has errors`() = runTest {

        whenever(client.get(AccountServiceImpl.URL)).thenReturn(response)
        whenever(gson.fromJson(response.bodyAsText(), AccountResults::class.java)).thenReturn(results)

        accountServiceImpl.load().test {
            assert(awaitItem() is ResponseState.OnSuccess)
            cancelAndIgnoreRemainingEvents()
        }
    }
}