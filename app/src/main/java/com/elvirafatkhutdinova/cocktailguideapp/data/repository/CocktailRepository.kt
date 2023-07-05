package com.elvirafatkhutdinova.cocktailguideapp.data.repository

import androidx.lifecycle.LiveData
import com.elvirafatkhutdinova.cocktailguideapp.api.RetrofitInstance
import com.elvirafatkhutdinova.cocktailguideapp.data.AppDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.model.Cocktail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CocktailRepository(private val database: AppDatabase) {

    val cocktails: LiveData<List<Cocktail>> = database.drinkDao().getAllCocktails()

    suspend fun refreshCocktails() {
        withContext(Dispatchers.IO) {
            val cocktailsResponse = RetrofitInstance.api.getCocktailByFirstLetter()
            database.drinkDao().insertCocktail(cocktailsResponse.drinks)
        }
    }

    fun getCocktail(id : Int) : LiveData<Cocktail> {
        return database.drinkDao().getCocktailById(id)
    }

    fun getCocktailsByCategory(category : String) : LiveData<List<Cocktail>> {
        return database.drinkDao().getCocktailsByCategory(category)
    }
}