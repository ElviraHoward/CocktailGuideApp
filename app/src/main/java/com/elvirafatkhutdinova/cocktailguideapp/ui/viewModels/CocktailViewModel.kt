package com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.Cocktail
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.CocktailsAndFavorites
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.RecentCocktail
import com.elvirafatkhutdinova.cocktailguideapp.domain.usecase.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class CocktailViewModel @Inject constructor(
    private val getCocktailListUseCase: GetCocktailListUseCase,
    private val getCocktailAndFavoriteListUseCase: GetCocktailAndFavoriteListUseCase,
    private val getCocktailByIdUseCase: GetCocktailByIdUseCase,
    private val getCocktailsAndFavoritesByCategoryUseCase: GetCocktailsAndFavoritesByCategoryUseCase,
    private val getCocktailsByFavoriteUseCase: GetCocktailsByFavoriteUseCase,
    private val loadCocktailsUseCase: LoadCocktailsUseCase,
    private val insertRecentCocktailUseCase: InsertRecentCocktailUseCase,
    private val getRecentCocktailListUseCase: GetRecentCocktailListUseCase
) : ViewModel() {

    val cocktailsAndFavorites = getCocktailAndFavoriteListUseCase.invoke()

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
            if (getCocktailListUseCase.invoke().value.isNullOrEmpty()) {
                try {
                    loadCocktailsUseCase.invoke()
                    _eventNetworkError.value = false
                    _isNetworkErrorShown.value = false
                } catch (networkError: IOException) {
                    if (getCocktailListUseCase.invoke().value.isNullOrEmpty()) {
                        _eventNetworkError.value = true
                    }
                }
            }
        }
    }

    fun getCocktailById(id: String) {
        val drinkLiveData = getCocktailByIdUseCase.invoke(id)
        drinkLiveData.observeForever { drink ->
            _cocktail.value = drink
        }
    }

    fun getCocktailsByCategory(category: String) {
        val drinkLiveData = getCocktailsAndFavoritesByCategoryUseCase.invoke(category)
        drinkLiveData.observeForever { drinks ->
            _cocktailList.value = drinks
        }
    }

    fun getCocktailsByFavorite() {
        val drinkLiveData = getCocktailsByFavoriteUseCase.invoke()
        drinkLiveData.observeForever { drinks ->
            _cocktailList.value = drinks
        }
    }

    fun insertRecentCocktail(idRecent: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val recentCocktail = RecentCocktail(idRecent = idRecent, timestamp = System.currentTimeMillis())
            insertRecentCocktailUseCase.invoke(recentCocktail)
        }
    }

    fun getRecentCocktails() = getRecentCocktailListUseCase.invoke()

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

}