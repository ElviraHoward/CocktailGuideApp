package com.elvirafatkhutdinova.cocktailguideapp.data.network

import com.elvirafatkhutdinova.cocktailguideapp.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : CocktailApi by lazy {
        retrofit.create(CocktailApi::class.java)
    }
}