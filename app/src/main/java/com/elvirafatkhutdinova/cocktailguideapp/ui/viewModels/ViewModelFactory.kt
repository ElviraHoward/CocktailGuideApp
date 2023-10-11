package com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CocktailViewModel::class.java)) {
            CocktailViewModel(application) as T
        } else if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            CategoryViewModel(application) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            FavoriteViewModel(application) as T
        } else throw IllegalArgumentException("Unknown view model")
    }
}