package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CocktailRepository
import javax.inject.Inject

class GetRandomCocktailUseCase @Inject constructor(private val repository: CocktailRepository) {
    suspend operator fun invoke() = repository.getRandomCocktail()
}