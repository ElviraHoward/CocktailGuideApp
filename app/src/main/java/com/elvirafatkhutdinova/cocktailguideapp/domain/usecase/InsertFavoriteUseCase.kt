package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class InsertFavoriteUseCase @Inject constructor(private val repository: FavoriteRepository){

    operator fun invoke(id: String) = repository.insertFavorite(id)
}