package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CocktailRepository
import javax.inject.Inject

class GetCocktailsAndFavoritesByCategoryUseCase @Inject constructor(private val repository: CocktailRepository) {

    operator fun invoke(category: String) = repository.getCocktailsAndFavoritesByCategory(category)
}