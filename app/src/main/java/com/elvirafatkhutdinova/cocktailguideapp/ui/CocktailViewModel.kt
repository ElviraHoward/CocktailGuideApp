package com.elvirafatkhutdinova.cocktailguideapp.ui

import android.app.Application
import androidx.lifecycle.*
import com.elvirafatkhutdinova.cocktailguideapp.data.AppDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.repository.CocktailRepository
import com.elvirafatkhutdinova.cocktailguideapp.data.model.Cocktail
import com.elvirafatkhutdinova.cocktailguideapp.data.repository.CategoryRepository
import kotlinx.coroutines.launch
import java.io.IOException

class CocktailViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryCocktail = CocktailRepository(AppDatabase.getDatabase(application))
    val cocktails = repositoryCocktail.cocktails
    private val _cocktail = MutableLiveData<Cocktail>()
    val cocktail : LiveData<Cocktail> get() = _cocktail

    private val _cocktailList = MutableLiveData<List<Cocktail>>()
    val cocktailList : LiveData<List<Cocktail>> get() = _cocktailList

    private val repositoryCategory = CategoryRepository(AppDatabase.getDatabase(application))
    val categories = repositoryCategory.categories

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val eventNetworkError : LiveData<Boolean>
        get() = _eventNetworkError

    val isNetworkErrorShown : LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        getCocktails()
        getCategories()
    }

    private fun getCocktails() {
        viewModelScope.launch {
            try {
                repositoryCocktail.refreshCocktails()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (networkError : IOException) {
                if (cocktails.value.isNullOrEmpty()) {
                    _eventNetworkError.value = true
                }
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            repositoryCategory.refreshCategories()
        }
    }

    fun getCocktailById(id : Int) {
        val drinkLiveData = repositoryCocktail.getCocktail(id)
        drinkLiveData.observeForever { drink ->
            _cocktail.value = drink
        }
    }

    fun getCocktailsByCategory(category : String) {
        val drinkLiveData = repositoryCocktail.getCocktailsByCategory(category)
        drinkLiveData.observeForever { drinks ->
            _cocktailList.value = drinks
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

}