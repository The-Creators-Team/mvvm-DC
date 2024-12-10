package com.creators.mvvm_week4.util

import com.creators.myapilearning.data.model.CocktailModel

sealed class ResponseState {
    //loading
    object Loading : ResponseState()

    //success
    data class Success(val result: CocktailModel) :
        ResponseState() //ideally we should make it generic

    //failure
    data class Fail(val error: String) : ResponseState()
}