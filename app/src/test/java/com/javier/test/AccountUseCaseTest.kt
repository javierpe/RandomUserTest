package com.javier.test

import com.javier.test.api.AccountServiceApi
import com.javier.test.useCases.AccountUseCase
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
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class AccountUseCaseTest {

    @Mock
    lateinit var service: AccountServiceApi

    private lateinit var useCase: AccountUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        useCase = AccountUseCase(service)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `load should be called when loadData is executed`() = runTest {
        useCase.loadData()
        verify(service).load()
    }
}