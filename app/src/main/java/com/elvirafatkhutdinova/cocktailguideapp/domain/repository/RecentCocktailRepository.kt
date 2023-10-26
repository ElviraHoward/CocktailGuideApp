package com.elvirafatkhutdinova.cocktailguideapp.domain.repository

import com.elvirafatkhutdinova.cocktailguideapp.domain.model.RecentCocktail

interface RecentCocktailRepository {

    fun insertRecentCocktail(recentCocktail: RecentCocktail)

    fun deleteRecentCocktail(idRecent: String)

}