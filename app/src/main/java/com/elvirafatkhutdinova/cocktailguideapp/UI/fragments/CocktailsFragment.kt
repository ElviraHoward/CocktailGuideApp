package com.elvirafatkhutdinova.cocktailguideapp.UI.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.UI.CocktailViewModel
import com.elvirafatkhutdinova.cocktailguideapp.UI.CocktailViewModelFactory
import com.elvirafatkhutdinova.cocktailguideapp.repository.Repository

class CocktailsFragment : Fragment(R.layout.fragment_cocktails) {

    private lateinit var viewModel : CocktailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repository()
        val viewModelFactory = CocktailViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CocktailViewModel::class.java)
        viewModel.getCocktails()
        viewModel.cocktailResponse.observe(this, Observer { response ->
            Log.d("Response", response.drinks.get(1).strDrink)
        })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}