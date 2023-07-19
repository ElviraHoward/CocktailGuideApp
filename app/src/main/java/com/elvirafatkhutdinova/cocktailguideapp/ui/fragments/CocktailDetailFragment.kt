package com.elvirafatkhutdinova.cocktailguideapp.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.FragmentCocktailDetailBinding
import com.elvirafatkhutdinova.cocktailguideapp.ui.CocktailViewModel
import com.elvirafatkhutdinova.cocktailguideapp.ui.adapters.RecipeAdapter

class CocktailDetailFragment : Fragment(R.layout.fragment_cocktail_detail) {

    private var _binding: FragmentCocktailDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CocktailViewModel by activityViewModels()
    private val idDrink by lazy { arguments?.getInt("idDrink") ?: 0 }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCocktailDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val idDrink = arguments?.getInt("idDrink") ?: 0
        viewModel.getCocktailById(idDrink)
        viewModel.cocktail.observe(viewLifecycleOwner) {
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
        setupMenu()
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.action_bar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_favorite -> {
                        viewModel.setFavoriteCocktail(true, idDrink)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
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