package com.javier.test.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.javier.test.components.AccountComponent
import com.javier.test.models.AccountModel
import com.javier.test.models.AccountName

@Composable
fun AccountContent(
    accounts: List<AccountModel>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items = accounts) {
            AccountComponent(account = it)
        }
    }
}

@Composable
@Preview
fun PreviewAccountScreen() {
    AccountContent(
        listOf(
            AccountModel(
                name = AccountName("Francisco")
            )
        )
    )
}