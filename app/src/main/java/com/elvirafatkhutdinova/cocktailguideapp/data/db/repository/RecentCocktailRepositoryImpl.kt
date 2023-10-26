package com.elvirafatkhutdinova.cocktailguideapp.data.db.repository

import com.elvirafatkhutdinova.cocktailguideapp.data.asDatabaseModel
import com.elvirafatkhutdinova.cocktailguideapp.data.db.dao.RecentCocktailDao
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.RecentCocktail
import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.RecentCocktailRepository
import javax.inject.Inject

class RecentCocktailRepositoryImpl @Inject constructor(private val recentCocktailDao: RecentCocktailDao) :
    RecentCocktailRepository {

    override fun insertRecentCocktail(recentCocktail: RecentCocktail) {
        if (recentCocktailDao.getCount() > 10) {
            deleteRecentCocktail(recentCocktailDao.getOldestRecentCocktail().idRecent)
        }
        recentCocktailDao.insertRecentCocktail(recentCocktail.asDatabaseModel())
    }

    override fun deleteRecentCocktail(idRecent: String) {
        recentCocktailDao.deleteRecentCocktail(idRecent)
    }
}