package com.elvirafatkhutdinova.cocktailguideapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elvirafatkhutdinova.cocktailguideapp.data.model.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun getAllCategories() : LiveData<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(categoryEntity: List<CategoryEntity>)
}