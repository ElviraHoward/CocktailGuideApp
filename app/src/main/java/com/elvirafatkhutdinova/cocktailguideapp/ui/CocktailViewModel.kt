package com.elvirafatkhutdinova.cocktailguideapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.model.CocktailsResponse
import com.elvirafatkhutdinova.cocktailguideapp.repository.Repository
import kotlinx.coroutines.launch

class CocktailViewModel(private val repository: Repository) : ViewModel() {

    val cocktailResponse : MutableLiveData<CocktailsResponse> = MutableLiveData()

    fun getCocktails() {
        viewModelScope.launch {
           val response : CocktailsResponse = repository.getCocktails()
            cocktailResponse.value = response
        }
    }
}