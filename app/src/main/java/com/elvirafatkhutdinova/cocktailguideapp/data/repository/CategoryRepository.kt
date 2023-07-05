package com.elvirafatkhutdinova.cocktailguideapp.data.repository

import androidx.lifecycle.LiveData
import com.elvirafatkhutdinova.cocktailguideapp.api.RetrofitInstance
import com.elvirafatkhutdinova.cocktailguideapp.data.AppDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository(private val database: AppDatabase) {

    val categories : LiveData<List<Category>> = database.categoryDao().getAllCategories()

    suspend fun refreshCategories() {
        withContext(Dispatchers.IO) {
            val categories = RetrofitInstance.api.getCategories().drinks
            database.categoryDao().insertCategory(categories)
        }
    }
}