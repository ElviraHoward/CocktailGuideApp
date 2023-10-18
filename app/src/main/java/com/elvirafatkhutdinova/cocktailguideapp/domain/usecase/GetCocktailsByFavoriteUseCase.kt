package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CocktailRepository

class GetCocktailsByFavoriteUseCase(private val repository: CocktailRepository) {

    operator fun invoke() = repository.getCocktailsByFavorite()
}