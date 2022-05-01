package com.javier.test

import com.javier.test.states.ResponseState
import com.javier.test.useCases.AccountUseCase
import com.javier.test.viewModels.AccountViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
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
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class AccountViewModelTest {

    @Mock
    lateinit var accountUseCase: AccountUseCase

    private lateinit var viewModel: AccountViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = AccountViewModel(accountUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadData should be called when load is executed`() = runTest {
        viewModel.load()
        verify(accountUseCase).loadData()
    }

    @Test
    fun `ResponseState should have a valid state`() = runTest {
        val state = ResponseState.Undefined
        val flow = flow { emit(state) }

        whenever(accountUseCase.loadData()).thenReturn(flow)

        viewModel.load()
        assert(viewModel.status.value == state)
    }
}