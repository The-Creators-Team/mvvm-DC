package com.creators.myapilearning.data.model


import com.google.gson.annotations.SerializedName

data class TimezoneModel(
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("offset")
    val offset: String? = ""
)