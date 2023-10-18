package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CategoryRepository

class LoadCategoriesUseCase(private val repository: CategoryRepository) {

    suspend operator fun invoke() = repository.loadData()
}