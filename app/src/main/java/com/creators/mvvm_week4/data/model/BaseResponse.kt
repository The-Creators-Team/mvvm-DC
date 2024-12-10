package com.creators.mvvm_week4.data.model

data class BaseResponse<T>(
    val `data`: List<T>,
    val message: String,
    val status: Boolean
)