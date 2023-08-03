package com.elvirafatkhutdinova.cocktailguideapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.elvirafatkhutdinova.cocktailguideapp.data.AppDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.model.asDomainModel
import com.elvirafatkhutdinova.cocktailguideapp.domain.Cocktail
import com.elvirafatkhutdinova.cocktailguideapp.domain.CocktailsAndFavorites
import com.elvirafatkhutdinova.cocktailguideapp.network.RetrofitInstance
import com.elvirafatkhutdinova.cocktailguideapp.network.model.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CocktailRepository(private val database: AppDatabase) {

    val cocktails: LiveData<List<Cocktail>> =
        database.drinkDao().getAllCocktails().map { it.asDomainModel() }

    val cocktailsAndFavorites: LiveData<List<CocktailsAndFavorites>> =
        database.drinkDao().getCocktailsAndFavorites().map { it.asDomainModel() }

    suspend fun refreshCocktails() {
        withContext(Dispatchers.IO) {
            val cocktailsResponse = RetrofitInstance.api.getCocktailByFirstLetter()
            database.drinkDao().insertCocktails(cocktailsResponse.asDatabaseModel())
        }
    }

    fun getCocktail(id: String): LiveData<Cocktail> {
        return database.drinkDao().getCocktailById(id).map { it.asDomainModel() }
    }

    fun getCocktailsByCategory(category: String): LiveData<List<CocktailsAndFavorites>> {
        return database.drinkDao().getCocktailsByCategory(category).map { it.asDomainModel() }
    }

    fun getCocktailsByFavorite(): LiveData<List<CocktailsAndFavorites>> {
        return database.drinkDao().getFavoriteCocktails().map { it.asDomainModel() }
    }
}