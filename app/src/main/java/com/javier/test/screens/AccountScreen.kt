package com.javier.test.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.javier.test.R
import com.javier.test.contents.AccountContent
import com.javier.test.states.ResponseState
import com.javier.test.viewModels.AccountViewModel

@Composable
fun AccountScreen(
    accountViewModel: AccountViewModel
) {
    val state = accountViewModel.status.collectAsState()

    LaunchedEffect(key1 = accountViewModel) {
        accountViewModel.load()
    }

    when (state.value) {
        is ResponseState.OnStart -> {
            Text(text = stringResource(R.string.loading))
        }

        is ResponseState.OnError -> {
            Text(text = stringResource(R.string.failed))
        }

        is ResponseState.OnSuccess -> {
            AccountContent(accounts = (state.value as ResponseState.OnSuccess).data.results)
        }

        is ResponseState.Undefined -> { /* load maybe */ }
    }
}

