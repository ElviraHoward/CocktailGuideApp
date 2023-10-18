package com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.data.db.repository.CategoryRepositoryImpl
import com.elvirafatkhutdinova.cocktailguideapp.domain.usecase.GetCategoryListUseCase
import com.elvirafatkhutdinova.cocktailguideapp.domain.usecase.LoadCategoriesUseCase
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : ViewModel() {

    private val repositoryCategory = CategoryRepositoryImpl(application)

    private val getCategoryListUseCase = GetCategoryListUseCase(repositoryCategory)
    private val loadCategoryUseCase = LoadCategoriesUseCase(repositoryCategory)

    val categories = getCategoryListUseCase.invoke()

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            loadCategoryUseCase.invoke()
        }
    }

}