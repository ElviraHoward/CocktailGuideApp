package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.model.RecentCocktail
import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.RecentCocktailRepository
import javax.inject.Inject

class InsertRecentCocktailUseCase @Inject constructor(private val repository: RecentCocktailRepository) {

    operator fun invoke(recentCocktail: RecentCocktail) = repository.insertRecentCocktail(recentCocktail)
}