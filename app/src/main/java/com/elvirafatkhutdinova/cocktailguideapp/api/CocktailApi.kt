package com.elvirafatkhutdinova.cocktailguideapp.api

import com.elvirafatkhutdinova.cocktailguideapp.model.DrinksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {

    @GET("search.php")
    suspend fun getCocktailByFirstLetter(@Query("f") letter : String = "a") : DrinksResponse
}