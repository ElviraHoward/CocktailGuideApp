package com.elvirafatkhutdinova.cocktailguideapp.domain.repository

import androidx.lifecycle.LiveData
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.Category

interface CategoryRepository {

    fun getCategoryList() : LiveData<List<Category>>

    suspend fun loadData()
}