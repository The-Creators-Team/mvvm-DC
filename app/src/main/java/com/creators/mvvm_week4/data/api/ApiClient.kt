package com.creators.myapilearning.data.api

import com.creators.myapilearning.data.model.CocktailModel
import com.creators.myapilearning.data.model.UserDetailModel
import retrofit2.http.GET

interface ApiClient {

    @GET(ApiDetails.ENDPOINT_USER)
    suspend fun getUser(
        //any parameters
    ): UserDetailModel //return type

    @GET(ApiDetails.ENDPOINT_COCKTAIL)
    suspend fun getCocktails(
        //any parameters
    ): CocktailModel //return type
}