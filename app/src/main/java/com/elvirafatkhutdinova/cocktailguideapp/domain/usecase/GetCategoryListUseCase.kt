package com.elvirafatkhutdinova.cocktailguideapp.domain.usecase

import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor(private val repository: CategoryRepository) {

    operator fun invoke() = repository.getCategoryList()
}