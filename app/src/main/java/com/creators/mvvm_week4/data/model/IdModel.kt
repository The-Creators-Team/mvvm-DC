package com.creators.myapilearning.data.model


import com.google.gson.annotations.SerializedName

data class IdModel(
    @SerializedName("name")
    val name: String? = "",
//    @SerializedName("value")
//    val value: AnyModel? = AnyModel()
)