package com.javier.test.models

import com.google.gson.annotations.SerializedName

data class AccountRegistered(
    @SerializedName("age") val age: Int
)
