package com.creators.myapilearning.data.api

import com.creators.mvvm_week4.data.model.BaseResponse
import com.creators.mvvm_week4.data.model.ItemMenu
import com.creators.myapilearning.data.model.CocktailModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {

    @GET(ApiDetails.ENDPOINT_COCKTAIL)
    suspend fun getCocktails(
        //any parameters
    ): CocktailModel //return type

    @POST(ApiDetails.ENDPOINT_GET_LIST_ITEM_MENU)
    suspend fun getItemMenu(@Body body: ItemMenuRequest): BaseResponse<ItemMenu>
}