package com.javier.test.models

import com.google.gson.annotations.SerializedName

data class AccountModel(
    @SerializedName("gender") val gender: String = "",
    @SerializedName("name") val name: AccountName,
    @SerializedName("location") val location: AccountLocation? = null,
    @SerializedName("email") val email: String = "",
    @SerializedName("picture") val picture: AccountPicture? = null,
    @SerializedName("registered") val registered: AccountRegistered? = null
)