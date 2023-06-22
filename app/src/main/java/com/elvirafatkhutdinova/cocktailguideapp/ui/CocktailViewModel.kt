package com.elvirafatkhutdinova.cocktailguideapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.data.DrinkDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.repository.DrinkRepository
import com.elvirafatkhutdinova.cocktailguideapp.data.model.Drink
import kotlinx.coroutines.launch
import java.io.IOException

class CocktailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DrinkRepository(DrinkDatabase.getDatabase(application))
    val cocktails = repository.drinks
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    private val _drink = MutableLiveData<Drink>()
    val drink : LiveData<Drink> get() = _drink

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
            _drink.value = drink
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

}