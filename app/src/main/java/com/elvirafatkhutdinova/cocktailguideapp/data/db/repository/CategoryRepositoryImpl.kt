package com.elvirafatkhutdinova.cocktailguideapp.data.db.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.elvirafatkhutdinova.cocktailguideapp.data.asDatabaseModel
import com.elvirafatkhutdinova.cocktailguideapp.data.asDomainModel
import com.elvirafatkhutdinova.cocktailguideapp.data.network.RetrofitInstance
import com.elvirafatkhutdinova.cocktailguideapp.data.db.AppDatabase
import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CategoryRepository
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepositoryImpl(private val application: Application) : CategoryRepository {

    private val categoryDao = AppDatabase.getDatabase(application).categoryDao()

    override fun getCategoryList(): LiveData<List<Category>> {
        return categoryDao.getAllCategories().map { list -> list.map { it.asDomainModel() } }
    }

    override suspend fun loadData() {
        withContext(Dispatchers.IO) {
            val categories = RetrofitInstance.api.getCategories()
            categoryDao.insertCategory(categories.asDatabaseModel())
        }
    }
}