package com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.data.db.repository.CocktailRepositoryImpl
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.Cocktail
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.CocktailsAndFavorites
import kotlinx.coroutines.launch
import java.io.IOException

class CocktailViewModel(application: Application) : ViewModel() {

    private val repositoryCocktail = CocktailRepositoryImpl(application)

    private val cocktails = repositoryCocktail.getCocktailList()
    val cocktailsAndFavorites = repositoryCocktail.getCocktailAndFavoriteList()

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
    }

    private fun getCocktails() {
        viewModelScope.launch {
            if (cocktails.value.isNullOrEmpty()) {
                try {
                    repositoryCocktail.loadData()
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

    fun getCocktailById(id: String) {
        val drinkLiveData = repositoryCocktail.getCocktailById(id)
        drinkLiveData.observeForever { drink ->
            _cocktail.value = drink
        }
    }

    fun getCocktailsByCategory(category: String) {
        val drinkLiveData = repositoryCocktail.getCocktailsAndFavoritesByCategory(category)
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

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

}