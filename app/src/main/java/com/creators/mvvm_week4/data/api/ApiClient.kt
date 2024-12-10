package com.creators.myapilearning.data.api

import com.creators.mvvm_week4.data.model.BaseResponse
import com.creators.mvvm_week4.data.model.ItemMenu
import com.creators.myapilearning.data.model.CocktailModel
import com.creators.myapilearning.data.model.UserDetailModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiClient {

    @GET(ApiDetails.ENDPOINT_USER)
    suspend fun getUser(
        //any parameters
    ): UserDetailModel //return type

    @GET(ApiDetails.ENDPOINT_COCKTAIL)
    suspend fun getCocktails(
        //any parameters
    ): CocktailModel //return type

    @POST(ApiDetails.ENDPOINT_GETLIST_ITEM_MENU)
    suspend fun userRegister(@Body  id_empresa: String ="1"): BaseResponse<ItemMenu>
}