package com.javier.test

import android.app.Application
import com.google.gson.Gson
import com.javier.test.api.AccountServiceApi
import com.javier.test.impl.AccountServiceImpl
import com.javier.test.useCases.AccountUseCase
import com.javier.test.viewModels.AccountViewModel
import io.ktor.client.HttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class App: Application() {

    private val accountModule = module {

        single<AccountServiceApi> {
            AccountServiceImpl(HttpClient(), Gson())
        }

        factory { AccountUseCase(get()) }

        viewModel { AccountViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(accountModule)
        }
    }
}