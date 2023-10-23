package com.elvirafatkhutdinova.cocktailguideapp

import android.app.Application
import com.elvirafatkhutdinova.cocktailguideapp.di.ApplicationComponent
import com.elvirafatkhutdinova.cocktailguideapp.di.DaggerApplicationComponent


class CocktailGuideApplication : Application() {

     val applicationComponent: ApplicationComponent by lazy {
         DaggerApplicationComponent.factory().create(this)
    }
}