package com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.domain.usecase.GetCategoryListUseCase
import com.elvirafatkhutdinova.cocktailguideapp.domain.usecase.LoadCategoriesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val loadCategoryUseCase: LoadCategoriesUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            loadCategoryUseCase.invoke()
        }
    }
    fun getCategories() = getCategoryListUseCase.invoke()

}