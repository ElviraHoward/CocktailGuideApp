package com.elvirafatkhutdinova.cocktailguideapp.di

import android.app.Application
import com.elvirafatkhutdinova.cocktailguideapp.ui.CocktailsActivity
import com.elvirafatkhutdinova.cocktailguideapp.ui.fragments.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity : CocktailsActivity)

    fun inject(cocktailsFragment: CocktailsFragment)

    fun inject(categoriesFragment: CategoriesFragment)

    fun inject(cocktailDetailFragment: CocktailDetailFragment)

    fun inject(cocktailsByCategoryFragment: CocktailsByCategoryFragment)

    fun inject(favoritesFragment: FavoritesFragment)

    @Component.Factory
    interface ComponentFactory {

        fun create(@BindsInstance application: Application) : ApplicationComponent
    }

}