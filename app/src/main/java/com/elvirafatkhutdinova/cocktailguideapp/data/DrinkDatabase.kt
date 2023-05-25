package com.elvirafatkhutdinova.cocktailguideapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elvirafatkhutdinova.cocktailguideapp.model.Drink

@Database(entities = [Drink::class], version = 1, exportSchema = false)
abstract class DrinkDatabase : RoomDatabase() {

    abstract fun drinkDao() : DrinkDao

    companion object{

        @Volatile
        private var INSTANCE : DrinkDatabase? = null

        fun getDatabase(context: Context) : DrinkDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DrinkDatabase::class.java,
                    "drinkDatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}