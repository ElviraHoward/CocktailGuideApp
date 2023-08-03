package com.elvirafatkhutdinova.cocktailguideapp.data.repository

import com.elvirafatkhutdinova.cocktailguideapp.data.AppDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.model.FavoriteEntity

class FavoriteRepository(private val database: AppDatabase) {

    fun insertFavorite(id: String) {
        val favEntity = FavoriteEntity(
            (1..100).random(),
            idFavorite = id
        )
        database.favoritesDao().insertFavorite(favEntity)
    }

    fun getFavoriteCocktailIds() {
        database.favoritesDao().getAllFavorites()
    }

    fun isFavoriteById(idFavorite: String): Boolean {
        val count = database.favoritesDao().isFavoriteById(idFavorite)
        return count > 0
    }

    fun deleteFavorite(idFavorite: String) {
        database.favoritesDao().deleteFavorite(idFavorite)
    }
}