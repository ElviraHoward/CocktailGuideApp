package com.elvirafatkhutdinova.cocktailguideapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.elvirafatkhutdinova.cocktailguideapp.data.db.model.CocktailEntity
import com.elvirafatkhutdinova.cocktailguideapp.data.db.model.CocktailsAndFavoritesEntity

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktail")
    fun getAllCocktails(): LiveData<List<CocktailEntity>>

    @Query("SELECT * FROM cocktail WHERE id_cocktail=:id")
    fun getCocktailById(id: String): LiveData<CocktailEntity>

    @Transaction
    @Query("SELECT * FROM cocktail WHERE category=:category")
    fun getCocktailsByCategory(category: String): LiveData<List<CocktailsAndFavoritesEntity>>

    @Transaction
    @Query("SELECT * FROM cocktail LEFT JOIN favorite on id_cocktail=id_favorite WHERE id_favorite IS NOT NULL")
    fun getFavoriteCocktails(): LiveData<List<CocktailsAndFavoritesEntity>>

    @Transaction
    @Query("SELECT * FROM cocktail")
    fun getCocktailsAndFavorites(): LiveData<List<CocktailsAndFavoritesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCocktails(cocktailEntities: List<CocktailEntity>)

    @Delete
    fun deleteCocktail(cocktailEntity: CocktailEntity)
}