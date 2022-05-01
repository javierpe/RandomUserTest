package com.javier.test.useCases

import com.javier.test.api.AccountServiceApi

class AccountUseCase(
    private val service: AccountServiceApi
) {

    suspend fun loadData() = service.load()
}