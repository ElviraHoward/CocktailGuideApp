package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CocktailRepository
import javax.inject.Inject

class GetCocktailByIdUseCase @Inject constructor(private val repository: CocktailRepository) {

    operator fun invoke(id: String) = repository.getCocktailById(id)
}