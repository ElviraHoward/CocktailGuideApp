package com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.domain.usecase.DeleteFavoriteUseCase
import com.elvirafatkhutdinova.cocktailguideapp.domain.usecase.InsertFavoriteUseCase
import com.elvirafatkhutdinova.cocktailguideapp.domain.usecase.IsFavoriteByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val isFavoriteByIdUseCase: IsFavoriteByIdUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    fun setFavoriteCocktail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            insertFavoriteUseCase.invoke(id)
        }
    }

    fun isFavoriteCocktail(id: String): LiveData<Boolean> {
        val resultLiveData = MutableLiveData<Boolean>()
        viewModelScope.launch {
            val isFavorite = withContext(Dispatchers.IO) {
                isFavoriteByIdUseCase.invoke(id)
            }
            withContext(Dispatchers.Main) {
                resultLiveData.value = isFavorite
            }
        }
        return resultLiveData
    }

    fun deleteFavorite(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteUseCase.invoke(id)
        }
    }
}