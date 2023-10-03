package com.elvirafatkhutdinova.cocktailguideapp.data.db.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.elvirafatkhutdinova.cocktailguideapp.data.asDatabaseModel
import com.elvirafatkhutdinova.cocktailguideapp.data.asDomainModel
import com.elvirafatkhutdinova.cocktailguideapp.data.network.RetrofitInstance
import com.elvirafatkhutdinova.cocktailguideapp.data.db.AppDatabase
import com.elvirafatkhutdinova.cocktailguideapp.domain.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository(private val database: AppDatabase) {

    val categories : LiveData<List<Category>> = database.categoryDao().getAllCategories().map { list -> list.map { it.asDomainModel() } }

    suspend fun refreshCategories() {
        withContext(Dispatchers.IO) {
            val categories = RetrofitInstance.api.getCategories()
            database.categoryDao().insertCategory(categories.asDatabaseModel())
        }
    }
}