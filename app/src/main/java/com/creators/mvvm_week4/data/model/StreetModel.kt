package com.creators.myapilearning.data.model


import com.google.gson.annotations.SerializedName

data class StreetModel(
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("number")
    val number: Int? = 0
)