package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class IsFavoriteByIdUseCase @Inject constructor(private val repository: FavoriteRepository) {

    operator fun invoke(idFavorite: String) = repository.isFavoriteById(idFavorite)
}