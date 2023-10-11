package com.elvirafatkhutdinova.cocktailguideapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.FragmentFavoritesBinding
import com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels.CocktailViewModel
import com.elvirafatkhutdinova.cocktailguideapp.ui.adapters.CocktailsAdapter
import com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels.ViewModelFactory

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CocktailViewModel by viewModels { ViewModelFactory(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCocktailsByFavorite()

        viewModel.cocktailList.observe(viewLifecycleOwner) { cocktailsAndFavorites ->
            val cocktailsAdapter = CocktailsAdapter(cocktailsAndFavorites)
            binding.rvFavoriteCocktails.adapter = cocktailsAdapter
            binding.rvFavoriteCocktails.layoutManager = GridLayoutManager(activity, 2)
            cocktailsAdapter.onItemClick {
                findNavController().navigate(FavoritesFragmentDirections.actionFavoriteListToCocktailDetailFromFavorites(it))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}