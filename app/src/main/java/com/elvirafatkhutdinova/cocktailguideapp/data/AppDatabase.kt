package com.elvirafatkhutdinova.cocktailguideapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.dao.CategoryDao
import com.elvirafatkhutdinova.cocktailguideapp.data.dao.CocktailDao
import com.elvirafatkhutdinova.cocktailguideapp.data.model.CategoryEntity
import com.elvirafatkhutdinova.cocktailguideapp.data.model.CocktailEntity

@Database(entities = [CocktailEntity::class, CategoryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun drinkDao() : CocktailDao
    abstract fun categoryDao() : CategoryDao

    companion object{

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "drinkDatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}