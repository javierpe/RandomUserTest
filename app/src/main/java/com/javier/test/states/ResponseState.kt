package com.javier.test.states

import com.javier.test.models.AccountModel
import com.javier.test.models.AccountResults
import java.lang.Exception

sealed class ResponseState {

    object Undefined: ResponseState()

    object OnStart: ResponseState()

    class OnSuccess(val data: AccountResults) : ResponseState()

    class OnError(val exception: Exception) : ResponseState()
}