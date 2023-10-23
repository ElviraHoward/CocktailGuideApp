package com.elvirafatkhutdinova.cocktailguideapp.data.db.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.elvirafatkhutdinova.cocktailguideapp.data.asDatabaseModel
import com.elvirafatkhutdinova.cocktailguideapp.data.asDomainModel
import com.elvirafatkhutdinova.cocktailguideapp.data.db.dao.CategoryDao
import com.elvirafatkhutdinova.cocktailguideapp.data.network.RetrofitInstance
import com.elvirafatkhutdinova.cocktailguideapp.domain.model.Category
import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val categoryDao: CategoryDao) :
    CategoryRepository {

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