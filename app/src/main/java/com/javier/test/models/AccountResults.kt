package com.javier.test.models

import com.google.gson.annotations.SerializedName

data class AccountResults(
    @SerializedName("results") val results: List<AccountModel>
)
