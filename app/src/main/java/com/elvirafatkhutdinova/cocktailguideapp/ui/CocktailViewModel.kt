package com.elvirafatkhutdinova.cocktailguideapp.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.data.AppDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.repository.CocktailRepository
import com.elvirafatkhutdinova.cocktailguideapp.data.model.Cocktail
import kotlinx.coroutines.launch
import java.io.IOException

class CocktailViewModel(application: Application) : ViewModel() {

    private val repository = CocktailRepository(AppDatabase.getDatabase(application))
    val cocktails = repository.cocktails
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    private val _cocktail = MutableLiveData<Cocktail>()
    val cocktail : LiveData<Cocktail> get() = _cocktail

    val eventNetworkError : LiveData<Boolean>
        get() = _eventNetworkError

    val isNetworkErrorShown : LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        getCocktails()
    }

    private fun getCocktails() {
        viewModelScope.launch {
            try {
                repository.refreshCocktails()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (networkError : IOException) {
                if (cocktails.value.isNullOrEmpty()) {
                    _eventNetworkError.value = true
                }
            }
        }
    }

    fun getCocktailById(id : Int) {
        val drinkLiveData = repository.getCocktail(id)
        drinkLiveData.observeForever { drink ->
            _cocktail.value = drink
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

}