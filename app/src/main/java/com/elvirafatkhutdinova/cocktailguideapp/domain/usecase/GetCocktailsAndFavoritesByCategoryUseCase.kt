package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CocktailRepository

class GetCocktailsAndFavoritesByCategoryUseCase(private val repository: CocktailRepository) {

    operator fun invoke(category: String) = repository.getCocktailsAndFavoritesByCategory(category)
}