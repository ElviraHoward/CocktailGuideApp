package com.elvirafatkhutdinova.cocktailguideapp.di

import androidx.lifecycle.ViewModel
import com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels.CategoryViewModel
import com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels.CocktailViewModel
import com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels.FavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CocktailViewModel::class)
    fun bindCocktailViewModel(impl: CocktailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    fun bindCategoryViewModel(impl: CategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    fun bindFavoriteViewModel(impl: FavoriteViewModel): ViewModel
}