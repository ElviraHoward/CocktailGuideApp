package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CategoryRepository
import javax.inject.Inject

class LoadCategoriesUseCase @Inject constructor(private val repository: CategoryRepository) {

    suspend operator fun invoke() = repository.loadData()
}