package com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.data.db.repository.FavoriteRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel(application: Application) : ViewModel() {

    private val favoriteRepository = FavoriteRepositoryImpl(application)

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
}