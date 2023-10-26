package com.elvirafatkhutdinova.cocktailguideapp.data.db.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.elvirafatkhutdinova.cocktailguideapp.data.asDatabaseModel
import com.elvirafatkhutdinova.cocktailguideapp.data.asDomainModel
import com.elvirafatkhutdinova.cocktailguideapp.data.db.dao.CocktailDao
import com.elvirafatkhutdinova.cocktailguideapp.data.network.RetrofitInstance
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.Cocktail
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.CocktailsAndFavorites
import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CocktailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(private val cocktailDao: CocktailDao) :
    CocktailRepository {

    override fun getCocktailList(): LiveData<List<Cocktail>> {
        return cocktailDao.getAllCocktails().map { list -> list.map { it.asDomainModel() } }
    }

    override fun getCocktailAndFavoriteList(): LiveData<List<CocktailsAndFavorites>> {
        return cocktailDao.getCocktailsAndFavorites()
            .map { list -> list.map { it.asDomainModel() } }
    }

    override fun getCocktailById(id: String): LiveData<Cocktail> {
        return cocktailDao.getCocktailById(id).map { it.asDomainModel() }
    }

    override fun getCocktailsAndFavoritesByCategory(category: String): LiveData<List<CocktailsAndFavorites>> {
        return cocktailDao.getCocktailsByCategory(category)
            .map { list -> list.map { it.asDomainModel() } }
    }

    override fun getCocktailsByFavorite(): LiveData<List<CocktailsAndFavorites>> {
        return cocktailDao.getFavoriteCocktails().map { list -> list.map { it.asDomainModel() } }
    }

    override fun getRecentCocktails(): LiveData<List<CocktailsAndFavorites>> {
        return cocktailDao.getRecentCocktails().map { list -> list.map { it.asDomainModel() } }
    }

    override suspend fun loadData() {
        withContext(Dispatchers.IO) {
            val cocktailsResponse = RetrofitInstance.api.getCocktailByFirstLetter()
            cocktailDao.insertCocktails(cocktailsResponse.asDatabaseModel())
        }
    }
}