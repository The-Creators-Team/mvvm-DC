package com.creators.myapilearning.data.model


import com.google.gson.annotations.SerializedName

data class DobModel(
    @SerializedName("age")
    val age: Int? = 0,
    @SerializedName("date")
    val date: String? = ""
)