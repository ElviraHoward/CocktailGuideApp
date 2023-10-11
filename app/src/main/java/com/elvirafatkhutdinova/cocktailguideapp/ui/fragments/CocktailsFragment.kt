package com.elvirafatkhutdinova.cocktailguideapp.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.FragmentCocktailsBinding
import com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels.CocktailViewModel
import com.elvirafatkhutdinova.cocktailguideapp.ui.adapters.CocktailsAdapter
import com.elvirafatkhutdinova.cocktailguideapp.ui.viewModels.ViewModelFactory

class CocktailsFragment : Fragment(R.layout.fragment_cocktails) {

    private var _binding: FragmentCocktailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CocktailViewModel by viewModels { ViewModelFactory(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }

        viewModel.cocktailsAndFavorites.observe(viewLifecycleOwner) { cocktails ->
            cocktails?.apply {
                val cocktailsAdapter = CocktailsAdapter(cocktails)
                binding.rvCocktails.adapter = cocktailsAdapter
                binding.rvCocktails.layoutManager = GridLayoutManager(activity, 2)
                cocktailsAdapter.onItemClick {
                    findNavController().navigate(
                        CocktailsFragmentDirections.actionCocktailListToCocktailDetail(
                            it
                        )
                    )
                }
            }
        }
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, R.string.network_error, Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}