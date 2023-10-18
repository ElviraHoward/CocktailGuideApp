package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CategoryRepository

class GetCategoryListUseCase(private val repository: CategoryRepository) {

    operator fun invoke() = repository.getCategoryList()
}