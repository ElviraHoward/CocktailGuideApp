package com.elvirafatkhutdinova.cocktailguideapp.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.data.AppDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.repository.CategoryRepository
import kotlinx.coroutines.launch
import java.util.Locale.Category

class CategoryViewModel(application: Application) : ViewModel() {

    private val repository = CategoryRepository(AppDatabase.getDatabase(application))
    val categories = repository.categories
    private val _category = MutableLiveData<Category>()
    val category : LiveData<Category> get() = _category

    private fun getCategories() {
        viewModelScope.launch {
            repository.refreshCategories()
        }
    }

}