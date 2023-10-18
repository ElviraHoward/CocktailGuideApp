package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.FavoriteRepository

class IsFavoriteByIdUseCase(private val repository: FavoriteRepository) {

    operator fun invoke(idFavorite: String) = repository.isFavoriteById(idFavorite)
}