package com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.data.db.AppDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.db.repository.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : ViewModel() {

    private val repositoryCategory = CategoryRepository(AppDatabase.getDatabase(application))
    val categories = repositoryCategory.categories

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            repositoryCategory.refreshCategories()
        }
    }

}