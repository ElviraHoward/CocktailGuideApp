package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CocktailRepository
import javax.inject.Inject

class GetCocktailAndFavoriteListUseCase @Inject constructor(private val repository: CocktailRepository) {

    operator fun invoke() = repository.getCocktailAndFavoriteList()
}