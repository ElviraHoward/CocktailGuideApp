package com.elvirafatkhutdinova.cocktailguideapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.FragmentFavoritesBinding
import com.elvirafatkhutdinova.cocktailguideapp.domain.Cocktail
import com.elvirafatkhutdinova.cocktailguideapp.ui.CocktailViewModel
import com.elvirafatkhutdinova.cocktailguideapp.ui.adapters.CocktailsAdapter

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CocktailViewModel by activityViewModels()
    private val cocktailsAdapter by lazy { CocktailsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavoriteCocktails.adapter = cocktailsAdapter
        binding.rvFavoriteCocktails.layoutManager = GridLayoutManager(activity, 2)

        viewModel.getCocktailsByFavorite()
        viewModel.cocktailList.observe(viewLifecycleOwner) {
            cocktailsAdapter.setData(it)
        }

        cocktailsAdapter.onItemClick {
            val bundle = Bundle().apply {
                putString("idDrink", it)
            }
            findNavController().navigate(
                R.id.action_favoritesFragment_to_cocktailDetailFragment,
                bundle
            )
        }
    }

}