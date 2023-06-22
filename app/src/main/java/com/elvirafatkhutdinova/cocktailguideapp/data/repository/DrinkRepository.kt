package com.elvirafatkhutdinova.cocktailguideapp.data.repository

import androidx.lifecycle.LiveData
import com.elvirafatkhutdinova.cocktailguideapp.api.RetrofitInstance
import com.elvirafatkhutdinova.cocktailguideapp.data.DrinkDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.model.Drink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DrinkRepository(private val database: DrinkDatabase) {

    val drinks: LiveData<List<Drink>> = database.drinkDao().getAllCocktails()

    suspend fun refreshCocktails() {
        withContext(Dispatchers.IO) {
            val cocktails = RetrofitInstance.api.getCocktailByFirstLetter()
            database.drinkDao().insertCocktail(cocktails.drinks)
        }
    }

    fun getCocktail(id : Int) : LiveData<Drink> {
        return database.drinkDao().getCocktailById(id)
    }
}