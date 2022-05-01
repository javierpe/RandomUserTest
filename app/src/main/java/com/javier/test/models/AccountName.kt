package com.javier.test.models

import com.google.gson.annotations.SerializedName

data class AccountName(
    @SerializedName("title") val title: String,
    @SerializedName("first") val first: String = "",
    @SerializedName("last") val last: String = ""
)