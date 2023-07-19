package com.elvirafatkhutdinova.cocktailguideapp.network

import com.elvirafatkhutdinova.cocktailguideapp.network.model.CategoryListResponse
import com.elvirafatkhutdinova.cocktailguideapp.network.model.CocktailListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {

    @GET("search.php")
    suspend fun getCocktailByFirstLetter(@Query("f") letter : String = "a") : CocktailListResponse
    @GET("list.php")
    suspend fun getCategories(@Query("c") list : String = "list") : CategoryListResponse
}