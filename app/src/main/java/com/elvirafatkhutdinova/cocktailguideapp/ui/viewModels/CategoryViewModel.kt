package com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.data.db.repository.CategoryRepositoryImpl
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : ViewModel() {

    private val repositoryCategory = CategoryRepositoryImpl(application)
    val categories = repositoryCategory.getCategoryList()

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            repositoryCategory.loadData()
        }
    }

}