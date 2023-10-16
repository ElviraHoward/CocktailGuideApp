package com.elvirafatkhutdinova.cocktailguideapp.data.db.dao

import androidx.room.*
import com.elvirafatkhutdinova.cocktailguideapp.data.db.model.FavoriteEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE id_favorite=:idFavorite")
    fun deleteFavorite(idFavorite : String)

    @Query("SELECT COUNT(*) FROM favorite WHERE id_favorite=:idFavorite LIMIT 1")
    fun isFavoriteById(idFavorite : String) : Int
}