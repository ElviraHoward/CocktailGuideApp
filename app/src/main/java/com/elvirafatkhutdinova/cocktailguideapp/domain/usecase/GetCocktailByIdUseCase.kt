package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CocktailRepository

class GetCocktailByIdUseCase(private val repository: CocktailRepository) {

    operator fun invoke(id: String) = repository.getCocktailById(id)
}