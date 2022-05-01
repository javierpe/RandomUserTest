package com.javier.test.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javier.test.states.ResponseState
import com.javier.test.useCases.AccountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AccountViewModel(
    private val accountUseCase: AccountUseCase
): ViewModel() {

    private val _status = MutableStateFlow<ResponseState>(ResponseState.Undefined)
    val status: StateFlow<ResponseState> = _status

    fun load() {
        viewModelScope.launch {
            accountUseCase.loadData().collect {
                _status.value = it
            }
        }
    }
}