package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CocktailRepository

class LoadCocktailsUseCase(private val repository: CocktailRepository) {

    suspend operator fun invoke() = repository.loadData()

}