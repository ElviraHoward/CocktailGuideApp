package com.elvirafatkhutdinova.cocktailguideapp.network.model

import com.elvirafatkhutdinova.cocktailguideapp.data.model.CategoryEntity

data class CategoryListResponse(
    val drinks: List<CategoryResponse>
)

fun CategoryListResponse.asDatabaseModel() : List<CategoryEntity> {
    return drinks.map {CategoryEntity(strCategory = it.strCategory)}
}