package com.elvirafatkhutdinova.cocktailguideapp.api

import com.elvirafatkhutdinova.cocktailguideapp.data.model.CategoriesResponse
import com.elvirafatkhutdinova.cocktailguideapp.data.model.CocktailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {

    @GET("search.php")
    suspend fun getCocktailByFirstLetter(@Query("f") letter : String = "a") : CocktailsResponse
    @GET("list.php")
    suspend fun getCategories(@Query("c") list : String = "list") : CategoriesResponse
}