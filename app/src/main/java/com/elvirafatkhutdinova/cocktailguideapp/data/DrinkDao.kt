package com.elvirafatkhutdinova.cocktailguideapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.elvirafatkhutdinova.cocktailguideapp.model.Drink

@Dao
interface DrinkDao {

    @Query("SELECT * FROM Drink")
    fun getAllCocktails() : LiveData<List<Drink>>

    @Insert
    fun upsertCocktail(drink: Drink) : Long

    @Delete
    fun deleteCocktail(drink: Drink)
}