package com.elvirafatkhutdinova.cocktailguideapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.elvirafatkhutdinova.cocktailguideapp.data.model.Drink

@Dao
interface DrinkDao {

    @Query("SELECT * FROM Drink")
    fun getAllCocktails() : LiveData<List<Drink>>

    @Query("SELECT * FROM Drink where id=:id")
    fun getCocktailById(id : Int) : LiveData<Drink>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCocktail(drinks: List<Drink>)

    @Delete
    fun deleteCocktail(drink: Drink)
}