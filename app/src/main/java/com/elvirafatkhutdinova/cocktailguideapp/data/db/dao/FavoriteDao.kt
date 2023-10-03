package com.elvirafatkhutdinova.cocktailguideapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.elvirafatkhutdinova.cocktailguideapp.data.db.model.FavoriteEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT id_favorite FROM favorite")
    fun getAllFavorites() : LiveData<List<String>>

    @Query("DELETE FROM favorite WHERE id_favorite=:idFavorite")
    fun deleteFavorite(idFavorite : String)

    @Query("SELECT COUNT(*) FROM favorite WHERE id_favorite=:idFavorite")
    fun isFavoriteById(idFavorite : String) : Int
}