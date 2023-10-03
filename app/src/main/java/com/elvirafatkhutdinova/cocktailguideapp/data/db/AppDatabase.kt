package com.elvirafatkhutdinova.cocktailguideapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.db.dao.CategoryDao
import com.elvirafatkhutdinova.cocktailguideapp.data.db.dao.CocktailDao
import com.elvirafatkhutdinova.cocktailguideapp.data.db.dao.FavoriteDao
import com.elvirafatkhutdinova.cocktailguideapp.data.db.model.CategoryEntity
import com.elvirafatkhutdinova.cocktailguideapp.data.db.model.CocktailEntity
import com.elvirafatkhutdinova.cocktailguideapp.data.db.model.FavoriteEntity

@Database(
    entities = [CocktailEntity::class, CategoryEntity::class, FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun drinkDao(): CocktailDao
    abstract fun categoryDao(): CategoryDao

    abstract fun favoritesDao(): FavoriteDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "drink_database.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}