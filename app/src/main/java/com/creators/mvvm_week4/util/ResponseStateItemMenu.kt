package com.creators.mvvm_week4.util

import com.creators.mvvm_week4.data.model.BaseResponse
import com.creators.mvvm_week4.data.model.ItemMenu
import com.creators.myapilearning.data.model.CocktailModel

sealed class ResponseStateItemMenu {
    //loading
    object Loading : ResponseStateItemMenu()

    //success
    data class Success(val result: BaseResponse<ItemMenu>) :
        ResponseStateItemMenu() //ideally we should make it generic

    //failure
    data class Fail(val error: String) : ResponseStateItemMenu()
}