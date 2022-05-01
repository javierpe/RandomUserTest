package com.javier.test.models

import com.google.gson.annotations.SerializedName

data class AccountLocation(
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String
)