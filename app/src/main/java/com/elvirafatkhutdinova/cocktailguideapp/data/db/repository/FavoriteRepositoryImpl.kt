package com.elvirafatkhutdinova.cocktailguideapp.data.db.repository

import android.app.Application
import com.elvirafatkhutdinova.cocktailguideapp.data.db.AppDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.db.model.FavoriteEntity
import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.FavoriteRepository

class FavoriteRepositoryImpl(private val application: Application) : FavoriteRepository {

    private val favoritesDao = AppDatabase.getDatabase(application).favoritesDao()

    override fun insertFavorite(id: String) {
        //TODO auto-generate id instead random
        val favEntity = FavoriteEntity(
            (1..100).random(),
            idFavorite = id
        )
        favoritesDao.insertFavorite(favEntity)
    }

    override fun isFavoriteById(idFavorite: String): Boolean {
        val count = favoritesDao.isFavoriteById(idFavorite)
        return count > 0
    }

    override fun deleteFavorite(idFavorite: String) {
        favoritesDao.deleteFavorite(idFavorite)
    }
}