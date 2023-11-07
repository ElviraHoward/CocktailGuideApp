package com.elvirafatkhutdinova.cocktailguideapp.data.db.repository

import com.elvirafatkhutdinova.cocktailguideapp.data.db.dao.FavoriteDao
import com.elvirafatkhutdinova.cocktailguideapp.data.db.model.FavoriteEntity
import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val favoritesDao: FavoriteDao) :
    FavoriteRepository {

    override fun insertFavorite(id: String) {
        val favEntity = FavoriteEntity(idFavorite = id)
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