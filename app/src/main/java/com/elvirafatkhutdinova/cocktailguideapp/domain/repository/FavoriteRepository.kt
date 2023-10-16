package com.elvirafatkhutdinova.cocktailguideapp.domain.repository

interface FavoriteRepository {

    fun insertFavorite(id: String)

    fun isFavoriteById(idFavorite: String): Boolean

    fun deleteFavorite(idFavorite: String)
}