package com.elvirafatkhutdinova.cocktailguideapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elvirafatkhutdinova.cocktailguideapp.repository.Repository

class CocktailViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CocktailViewModel(repository) as T
    }
}