package com.javier.test.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.javier.test.R
import com.javier.test.models.AccountModel
import com.javier.test.models.AccountName

@Composable
fun AccountComponent(
    account: AccountModel
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        
        AsyncImage(
            model = account.picture?.large.orEmpty(),
            contentDescription = account.name.first,
            modifier = Modifier.clip(CircleShape)
        )
        
        Column(
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {

            Text(text = stringResource(R.string.name, account.name.first))

            account.email.takeIf { it.isNotBlank() }?.let {
                Text(text = stringResource(R.string.email, it))
            }

            account.gender.takeIf { it.isNotBlank() }?.let {
                Text(text = stringResource(R.string.gender, it))
            }

            account.registered?.let {
                Text(text = stringResource(R.string.age, it.age.toString()))
            }

            account.location?.let {
                Text(text = stringResource(R.string.city, it.city))
            }
        }
    }
}

@Composable
fun PreviewAccountComponent() {
    AccountComponent(
        AccountModel(
            name = AccountName("Francisco")
        )
    )
}