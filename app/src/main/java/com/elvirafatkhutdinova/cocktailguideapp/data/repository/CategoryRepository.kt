package com.elvirafatkhutdinova.cocktailguideapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.elvirafatkhutdinova.cocktailguideapp.network.RetrofitInstance
import com.elvirafatkhutdinova.cocktailguideapp.data.AppDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.model.asDomainModel
import com.elvirafatkhutdinova.cocktailguideapp.network.model.asDatabaseModel
import com.elvirafatkhutdinova.cocktailguideapp.domain.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository(private val database: AppDatabase) {

    val categories : LiveData<List<Category>> = database.categoryDao().getAllCategories().map { it.asDomainModel() }

    suspend fun refreshCategories() {
        withContext(Dispatchers.IO) {
            val categories = RetrofitInstance.api.getCategories()
            database.categoryDao().insertCategory(categories.asDatabaseModel())
        }
    }
}