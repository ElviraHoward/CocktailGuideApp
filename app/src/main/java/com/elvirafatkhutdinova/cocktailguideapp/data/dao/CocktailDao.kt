package com.elvirafatkhutdinova.cocktailguideapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.elvirafatkhutdinova.cocktailguideapp.data.model.Cocktail

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktail")
    fun getAllCocktails() : LiveData<List<Cocktail>>

    @Query("SELECT * FROM cocktail WHERE id=:id")
    fun getCocktailById(id : Int) : LiveData<Cocktail>

    @Query("SELECT * FROM cocktail WHERE category=:category")
    fun getCocktailsByCategory(category : String) : LiveData<List<Cocktail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCocktail(cocktails: List<Cocktail>)

    @Delete
    fun deleteCocktail(cocktail: Cocktail)
}