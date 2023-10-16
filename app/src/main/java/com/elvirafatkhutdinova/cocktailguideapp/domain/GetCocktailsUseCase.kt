package com.elvirafatkhutdinova.cocktailguideapp.domain

import com.elvirafatkhutdinova.cocktailguideapp.data.db.repository.CocktailRepositoryImpl

class GetCocktailsUseCase(private val repository: CocktailRepositoryImpl) {

    operator fun invoke() = repository.getCocktailList()
}