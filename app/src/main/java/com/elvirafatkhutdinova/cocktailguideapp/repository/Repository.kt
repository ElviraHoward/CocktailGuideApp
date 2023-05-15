package com.elvirafatkhutdinova.cocktailguideapp.repository

import com.elvirafatkhutdinova.cocktailguideapp.api.RetrofitInstance
import com.elvirafatkhutdinova.cocktailguideapp.model.DrinksResponse

class Repository {

    suspend fun getCocktails() : DrinksResponse {
       return RetrofitInstance.api.getCocktailByFirstLetter()
    }
}