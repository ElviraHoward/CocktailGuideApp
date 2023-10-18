package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CocktailRepository

class GetCocktailListUseCase(private val repository: CocktailRepository) {

    operator fun invoke() = repository.getCocktailList()
}