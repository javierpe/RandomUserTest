package com.javier.test.models

import com.google.gson.annotations.SerializedName

data class AccountPicture(
    @SerializedName("large") val large: String
)
