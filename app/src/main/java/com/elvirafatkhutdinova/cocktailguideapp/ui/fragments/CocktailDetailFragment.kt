package com.elvirafatkhutdinova.cocktailguideapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.FragmentCocktailDetailBinding
import com.elvirafatkhutdinova.cocktailguideapp.ui.CocktailViewModel
import com.elvirafatkhutdinova.cocktailguideapp.ui.adapters.RecipeAdapter

class CocktailDetailFragment : Fragment(R.layout.fragment_cocktail_detail) {

    private var _binding : FragmentCocktailDetailBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel : CocktailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCocktailDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val idDrink = arguments?.getInt("idDrink") ?: 0
        sharedViewModel.getCocktailById(idDrink)
        sharedViewModel.drink.observe(viewLifecycleOwner) {
            binding.drinkName.text = it.strDrink
            binding.categoryCocktail.text = it.strCategory
            binding.glassCocktail.text = it.strGlass
            binding.typeCocktail.text = it.strAlcoholic
            binding.instructionsText.text = it.strInstructions
            Glide.with(this).load(it.strDrinkThumb).into(binding.imageView)
            val recipeAdapter = RecipeAdapter(it.getIngredientMeasures())
            binding.ingredientsList.adapter = recipeAdapter
            binding.ingredientsList.layoutManager = LinearLayoutManager(activity)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
    }
    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
    }
}