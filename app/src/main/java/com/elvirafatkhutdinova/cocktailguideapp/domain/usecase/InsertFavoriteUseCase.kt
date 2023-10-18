package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.FavoriteRepository

class InsertFavoriteUseCase(private val repository: FavoriteRepository){

    operator fun invoke(id: String) = repository.insertFavorite(id)
}