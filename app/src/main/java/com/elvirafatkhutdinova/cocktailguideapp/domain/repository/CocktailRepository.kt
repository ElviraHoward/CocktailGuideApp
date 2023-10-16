package com.elvirafatkhutdinova.cocktailguideapp.domain.repository

import androidx.lifecycle.LiveData
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.Cocktail
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.CocktailsAndFavorites

interface CocktailRepository {

    fun getCocktailList(): LiveData<List<Cocktail>>

    fun getCocktailAndFavoriteList(): LiveData<List<CocktailsAndFavorites>>

    fun getCocktailById(id: String): LiveData<Cocktail>

    fun getCocktailsAndFavoritesByCategory(category: String): LiveData<List<CocktailsAndFavorites>>

    fun getCocktailsByFavorite(): LiveData<List<CocktailsAndFavorites>>

    suspend fun loadData()
}