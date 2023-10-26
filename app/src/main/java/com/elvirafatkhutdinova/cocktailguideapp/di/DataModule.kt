package com.elvirafatkhutdinova.cocktailguideapp.di

import android.app.Application
import com.elvirafatkhutdinova.cocktailguideapp.data.db.AppDatabase
import com.elvirafatkhutdinova.cocktailguideapp.data.db.dao.CategoryDao
import com.elvirafatkhutdinova.cocktailguideapp.data.db.dao.CocktailDao
import com.elvirafatkhutdinova.cocktailguideapp.data.db.dao.FavoriteDao
import com.elvirafatkhutdinova.cocktailguideapp.data.db.dao.RecentCocktailDao
import com.elvirafatkhutdinova.cocktailguideapp.data.db.repository.CategoryRepositoryImpl
import com.elvirafatkhutdinova.cocktailguideapp.data.db.repository.CocktailRepositoryImpl
import com.elvirafatkhutdinova.cocktailguideapp.data.db.repository.FavoriteRepositoryImpl
import com.elvirafatkhutdinova.cocktailguideapp.data.db.repository.RecentCocktailRepositoryImpl
import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CategoryRepository
import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.CocktailRepository
import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.FavoriteRepository
import com.elvirafatkhutdinova.cocktailguideapp.domain.repository.RecentCocktailRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCocktailRepository(impl: CocktailRepositoryImpl): CocktailRepository

    @Binds
    fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    fun bindFavoriteRepository(impl: FavoriteRepositoryImpl): FavoriteRepository

    @Binds
    fun bindRecentCocktailRepository(impl: RecentCocktailRepositoryImpl): RecentCocktailRepository

    companion object {

        @Provides
        fun provideCocktailDao(application: Application): CocktailDao {
            return AppDatabase.getDatabase(application).cocktailDao()
        }

        @Provides
        fun provideCategoryDao(application: Application): CategoryDao {
            return AppDatabase.getDatabase(application).categoryDao()
        }

        @Provides
        fun provideFavoriteDao(application: Application): FavoriteDao {
            return AppDatabase.getDatabase(application).favoritesDao()
        }

        @Provides
        fun provideRecentCocktailDao(application: Application): RecentCocktailDao {
            return AppDatabase.getDatabase(application).recentCocktailDao()
        }
    }
}