package com.elvirafatkhutdinova.cocktailguideapp.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.repository.Repository

class CocktailsActivity : AppCompatActivity() {

    private lateinit var viewModel : CocktailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktails)

        val repository = Repository()
        val viewModelFactory = CocktailViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CocktailViewModel::class.java)
        viewModel.getCocktails()
        viewModel.cocktailResponse.observe(this, Observer { response ->
            Log.d("Response", response.drinks.get(1).strDrink)
        })
    }
}