package com.elvirafatkhutdinova.cocktailguideapp.UI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.model.DrinksResponse
import com.elvirafatkhutdinova.cocktailguideapp.repository.Repository
import kotlinx.coroutines.launch

class CocktailViewModel(private val repository: Repository) : ViewModel() {

    val cocktailResponse : MutableLiveData<DrinksResponse> = MutableLiveData()

    fun getCocktails() {
        viewModelScope.launch {
           val response : DrinksResponse = repository.getCocktails()
            cocktailResponse.value = response
        }
    }
}