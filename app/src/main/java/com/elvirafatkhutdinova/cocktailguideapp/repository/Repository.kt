package com.elvirafatkhutdinova.cocktailguideapp.repository

import com.elvirafatkhutdinova.cocktailguideapp.api.RetrofitInstance
import com.elvirafatkhutdinova.cocktailguideapp.model.CocktailsResponse

class Repository {

    suspend fun getCocktails() : CocktailsResponse {
       return RetrofitInstance.api.getCocktailByFirstLetter()
    }
}