package com.elvirafatkhutdinova.cocktailguideapp.data.db.dao

import androidx.room.*
import com.elvirafatkhutdinova.cocktailguideapp.data.db.model.RecentCocktailEntity

@Dao
interface RecentCocktailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecentCocktail(recentCocktailEntity: RecentCocktailEntity)

    @Query("SELECT COUNT(id_recent_cocktail) FROM recent_cocktail")
    fun getCount(): Int

    @Query("SELECT * FROM recent_cocktail ORDER BY id_recent_cocktail ASC LIMIT 1")
    fun getOldestRecentCocktail(): RecentCocktailEntity

    @Query("DELETE FROM recent_cocktail WHERE id_recent_cocktail=:idRecent")
    fun deleteRecentCocktail(idRecent: String)
}