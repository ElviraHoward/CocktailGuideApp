package com.elvirafatkhutdinova.cocktailguideapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.elvirafatkhutdinova.cocktailguideapp.R
import com.elvirafatkhutdinova.cocktailguideapp.databinding.FragmentCocktailsBinding
import com.elvirafatkhutdinova.cocktailguideapp.data.model.Cocktail
import com.elvirafatkhutdinova.cocktailguideapp.ui.CocktailViewModel
import com.elvirafatkhutdinova.cocktailguideapp.ui.adapters.CocktailsAdapter

class CocktailsFragment : Fragment(R.layout.fragment_cocktails) {

    private var _binding : FragmentCocktailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel : CocktailViewModel by activityViewModels()
    private val cocktailsAdapter by lazy { CocktailsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCocktailsBinding.inflate(layoutInflater, container, false)

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cocktailsRecyclerView.adapter = cocktailsAdapter
        binding.cocktailsRecyclerView.layoutManager = GridLayoutManager(activity, 2)

        viewModel.cocktails.observe(viewLifecycleOwner, Observer<List<Cocktail>> { cocktail ->
            cocktail?.apply {
                cocktailsAdapter.setData(cocktail)
            }
        })

        cocktailsAdapter.onItemClick {
            val bundle = Bundle().apply {
                putInt("idDrink", it)
            }
            findNavController().navigate(R.id.action_cocktailsFragment_to_cocktailDetailFragment, bundle)
        }
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }
}