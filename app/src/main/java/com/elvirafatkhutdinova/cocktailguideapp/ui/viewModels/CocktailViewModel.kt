package com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.RecentCocktail
import com.elvirafatkhutdinova.cocktailguideapp.domain.usecase.*
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
    private val getRecentCocktailListUseCase: GetRecentCocktailListUseCase,
    private val getRandomCocktailUseCase: GetRandomCocktailUseCase
) : ViewModel() {

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
                    _eventNetworkError.value = true
                }
            }
        }
    }

    fun getCocktailsAndFavorites() = getCocktailAndFavoriteListUseCase.invoke()
    fun getCocktailById(id: String) = getCocktailByIdUseCase.invoke(id)
    fun getCocktailsByCategory(category: String) =
        getCocktailsAndFavoritesByCategoryUseCase.invoke(category)

    fun getCocktailsByFavorite() = getCocktailsByFavoriteUseCase.invoke()
    fun getRecentCocktails() = getRecentCocktailListUseCase.invoke()

    fun insertRecentCocktail(idRecent: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val recentCocktail =
                RecentCocktail(idRecent = idRecent, timestamp = System.currentTimeMillis())
            insertRecentCocktailUseCase.invoke(recentCocktail)
        }
    }

    fun getRandomCocktail(completion: (String) -> Unit) {
        viewModelScope.launch {
            val result = getRandomCocktailUseCase.invoke()
            if (result.isSuccess) {
                val idCocktail = result.getOrDefault("")
                completion(idCocktail)
            } else {
                completion("")
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}