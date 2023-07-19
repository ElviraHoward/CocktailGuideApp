package com.elvirafatkhutdinova.cocktailguideapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.elvirafatkhutdinova.cocktailguideapp.data.model.CocktailEntity

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktail")
    fun getAllCocktails() : LiveData<List<CocktailEntity>>

    @Query("SELECT * FROM cocktail WHERE id=:id")
    fun getCocktailById(id : Int) : LiveData<CocktailEntity>

    @Query("SELECT * FROM cocktail WHERE category=:category")
    fun getCocktailsByCategory(category : String) : LiveData<List<CocktailEntity>>

    @Query("SELECT * FROM cocktail WHERE is_favorite=:isFavorite")
    fun getCocktailsByFavorite(isFavorite : Boolean) : LiveData<List<CocktailEntity>>

    @Query("UPDATE cocktail SET is_favorite=:isFavorite WHERE id=:id")
    fun updateCocktailByFavorite(isFavorite : Boolean, id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCocktails(cocktailEntities: List<CocktailEntity>)

    @Delete
    fun deleteCocktail(cocktailEntity: CocktailEntity)
}