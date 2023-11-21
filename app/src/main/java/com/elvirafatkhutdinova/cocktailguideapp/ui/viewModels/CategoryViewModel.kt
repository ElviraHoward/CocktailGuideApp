package com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elvirafatkhutdinova.cocktailguideapp.domain.usecase.GetCategoryListUseCase
import com.elvirafatkhutdinova.cocktailguideapp.domain.usecase.LoadCategoriesUseCase
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val loadCategoryUseCase: LoadCategoriesUseCase
) : ViewModel() {

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        viewModelScope.launch {
            if (getCategoryListUseCase.invoke().value.isNullOrEmpty()) {
                try {
                    loadCategoryUseCase.invoke()
                    _eventNetworkError.value = false
                    _isNetworkErrorShown.value = false
                } catch (networkError: IOException) {
                    _eventNetworkError.value = true
                }
            }
        }
    }
    fun getCategories() = getCategoryListUseCase.invoke()

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

}