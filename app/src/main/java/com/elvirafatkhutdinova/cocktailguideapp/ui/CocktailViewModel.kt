package com.elvirafatkhutdinova.cocktailguideapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.data.db.AppDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.db.repository.CategoryRepository
import com.elvirafatkhutdinova.cocktailguideapp.data.db.repository.CocktailRepository
import com.elvirafatkhutdinova.cocktailguideapp.data.db.repository.FavoriteRepository
import com.elvirafatkhutdinova.cocktailguideapp.domain.Cocktail
import com.elvirafatkhutdinova.cocktailguideapp.domain.CocktailsAndFavorites
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class CocktailViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryCocktail = CocktailRepository(AppDatabase.getDatabase(application))
    private val repositoryCategory = CategoryRepository(AppDatabase.getDatabase(application))
    private val favoriteRepository = FavoriteRepository(AppDatabase.getDatabase(application))

    private val cocktails = repositoryCocktail.cocktails
    val cocktailsAndFavorites = repositoryCocktail.cocktailsAndFavorites
    val categories = repositoryCategory.categories

    private val _cocktail = MutableLiveData<Cocktail>()
    val cocktail: LiveData<Cocktail> get() = _cocktail

    private val _cocktailList = MutableLiveData<List<CocktailsAndFavorites>>()
    val cocktailList: LiveData<List<CocktailsAndFavorites>> get() = _cocktailList

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        getCocktails()
        getCategories()
    }

    private fun getCocktails() {
        viewModelScope.launch {
            if (cocktails.value.isNullOrEmpty()) {
                try {
                    repositoryCocktail.refreshCocktails()
                    _eventNetworkError.value = false
                    _isNetworkErrorShown.value = false
                } catch (networkError: IOException) {
                    if (cocktails.value.isNullOrEmpty()) {
                        _eventNetworkError.value = true
                    }
                }
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            repositoryCategory.refreshCategories()
        }
    }

    fun getCocktailById(id: String) {
        val drinkLiveData = repositoryCocktail.getCocktail(id)
        drinkLiveData.observeForever { drink ->
            _cocktail.value = drink
        }
    }

    fun getCocktailsByCategory(category: String) {
        val drinkLiveData = repositoryCocktail.getCocktailsByCategory(category)
        drinkLiveData.observeForever { drinks ->
            _cocktailList.value = drinks
        }
    }

    fun getCocktailsByFavorite() {
        val drinkLiveData = repositoryCocktail.getCocktailsByFavorite()
        drinkLiveData.observeForever { drinks ->
            _cocktailList.value = drinks
        }
    }

    fun setFavoriteCocktail(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            favoriteRepository.insertFavorite(id)
        }
    }

    fun isFavoriteCocktail(id: String): LiveData<Boolean> {
        val resultLiveData = MutableLiveData<Boolean>()
        viewModelScope.launch {
            val isFavorite = withContext(Dispatchers.IO) {
                favoriteRepository.isFavoriteById(id)
            }
            resultLiveData.value = isFavorite
        }
        return resultLiveData
    }

    fun deleteFavorite(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            favoriteRepository.deleteFavorite(id)
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

}